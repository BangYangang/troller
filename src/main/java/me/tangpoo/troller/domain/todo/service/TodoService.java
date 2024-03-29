package me.tangpoo.troller.domain.todo.service;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.board.repository.BoardRepository;
import me.tangpoo.troller.domain.todo.dto.ToDoResponseDto;
import me.tangpoo.troller.domain.todo.dto.ToDoResponseDto;
import me.tangpoo.troller.domain.todo.dto.TodoMoveRequestDto;
import me.tangpoo.troller.domain.todo.dto.TodoRequestDto;
import me.tangpoo.troller.domain.todo.entity.Todo;
import me.tangpoo.troller.domain.todo.repository.TodoQueryRepository;
import me.tangpoo.troller.domain.todo.repository.TodoRepository;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final BoardRepository boardRepository;
    private final TodoQueryRepository todoQueryRepository;
    private final RedissonClient redissonClient;

    private static final String LOCK_KEY = "counterLock";
    /**
     * Todo 생성
     *
     * @param requestDto todo 생성 요청정보
     * @param boardId    보드 아이디
     */
    public void createTodo(Long boardId, TodoRequestDto requestDto) {
        Board board = findBoardId(boardId);
        todoRepository.save(new Todo(board, requestDto.getTodoName()));
    }


    /**
     * Todo 조회
     *
     * @param boardId 보드 아이디
     * @return todo 조회 결과
     */
    @Cacheable(cacheNames = "getTodos", key = "#boardId", cacheManager = "rcm")
    public List<ToDoResponseDto> getTodos(Long boardId) {

        List<Todo> todoList = todoRepository.findByBoard_BoardId(boardId);

        List<ToDoResponseDto> toDoResponseList = new ArrayList<>();

        for (Todo todo : todoList) {
            toDoResponseList.add(new ToDoResponseDto(todo));
        }
        return toDoResponseList;

    }

    /**
     * Todo 수정
     *  @param boardId        보드 아이디
     *
     * @param todoId         todo 아이디
     * @param todoRequestDto 수정 요청 정보
     * @param memberId
     */
    @Transactional
    public void updateTodo(Long boardId, Long todoId, TodoRequestDto todoRequestDto,
        Long memberId) throws InterruptedException{

        RLock lock = redissonClient.getFairLock(LOCK_KEY);

        try {
            boolean isLocked = lock.tryLock(10, 60, TimeUnit.SECONDS);
            if (isLocked) {
                try {
                    // 해당 유저의 보드, todo 확인
                    Todo findTodo = findTodo(boardId, todoId, memberId);

                    findTodo.update(todoRequestDto);
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {

            System.out.println("분산 락");
            Thread.sleep(50);
        }



    }

    /**
     * Todo 삭제
     *
     * @param boardId 보드 아이디
     * @param todoId  todo 아이디
     */
    @Transactional
    public void deleteTodo(Long boardId, Long todoId, Long memberId) {
        // 해당 유저의 보드, todo 확인
        Todo findTodo = findTodo(boardId, todoId, memberId);

        todoRepository.delete(findTodo);
    }


    private Board findBoardId(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() ->
            new IllegalArgumentException("선택한 보드는 존재하지 않습니다."));
    }


    private Todo findTodo(Long boardId, Long todoId, Long memberId) {
        Todo findTodo = todoRepository.findById(todoId).orElseThrow(() ->
            new IllegalArgumentException("선택한 todo는 존재하지 않습니다."));

        if (!findTodo.getBoard().getMember().getMemberId().equals(memberId)) {
            throw new IllegalArgumentException("자신의 보드가 아닙니다.");
        }

        if (!findTodo.getBoard().getBoardId().equals(boardId)) {
            throw new IllegalArgumentException("선택한 보드의 todo가 아닙니다.");
        }

        return findTodo;
    }

    @Transactional
    public void moveTodo(Long boardId, TodoMoveRequestDto todoRequestDto) throws InterruptedException{
        while (true){
            try{
                for (TodoMoveRequestDto request : todoRequestDto.getDtoList()) {
                    todoQueryRepository.updateOrder(request,boardId);
                }
                break;
            }catch (ObjectOptimisticLockingFailureException e){
                System.out.println("낙관 락");
                Thread.sleep(50);
            }
        }

    }
}
