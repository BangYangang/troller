package me.tangpoo.troller.domain.todo.service;

import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.board.repository.BoardRepository;
import me.tangpoo.troller.domain.todo.dto.ToDoResponse;
import me.tangpoo.troller.domain.todo.dto.TodoMoveRequestDto;
import me.tangpoo.troller.domain.todo.dto.TodoRequestDto;
import me.tangpoo.troller.domain.todo.entity.Todo;
import me.tangpoo.troller.domain.todo.repository.Todorepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final Todorepository todorepository;
    private final BoardRepository boardRepository;

    /**
     * Todo 생성
     *
     * @param requestDto todo 생성 요청정보
     * @param boardId    보드 아이디
     */
    public void createTodo(Long boardId, TodoRequestDto requestDto) {
        Board board = findBoardId(boardId);
        todorepository.save(new Todo(board, requestDto.getTodoName()));
    }


    /**
     * Todo 조회
     *
     * @param boardId 보드 아이디
     * @return todo 조회 결과
     */
    public List<ToDoResponse> getTodos(Long boardId) {
        List<Todo> todoList = todorepository.findByBoard_BoardId(boardId);

        List<ToDoResponse> toDoResponseList = new ArrayList<>();

        for (Todo todo : todoList) {
            toDoResponseList.add(new ToDoResponse(todo));
        }
        return toDoResponseList;

    }

    /**
     * Todo 수정
     *
     * @param boardId        보드 아이디
     * @param todoId         todo 아이디
     * @param todoRequestDto 수정 요청 정보
     */
    @Transactional
    public void updateTodo(Long boardId, Long todoId, TodoRequestDto todoRequestDto) {
        Todo findTodo = findTodo(boardId, todoId);

        findTodo.update(todoRequestDto);

    }

    /**
     * Todo 삭제
     *
     * @param boardId 보드 아이디
     * @param todoId  todo 아이디
     */
    @Transactional
    public void deleteTodo(Long boardId, Long todoId) {
        Todo findTodo = findTodo(boardId, todoId);
        todorepository.delete(findTodo);
    }


    private Board findBoardId(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(() ->
            new IllegalArgumentException("선택한 보드는 존재하지 않습니다."));
    }


    private Todo findTodo(Long boardId, Long todoId) {
        Todo findTodo = todorepository.findById(todoId).orElseThrow(() ->
            new IllegalArgumentException("선택한 todo는 존재하지 않습니다."));

        if (!findTodo.getBoard().getBoardId().equals(boardId)) {
            throw new IllegalArgumentException("선택한 보드의 todo가 아닙니다.");
        }

        return findTodo;
    }

    @Transactional
    public void moveTodo(Long boardId, TodoMoveRequestDto todoRequestDto) {

        List<Todo> todoList = todorepository.findByBoard_BoardId(boardId);
//
//        List<Long> todoIdList = todoList.stream().map(Todo::getId).toList();

        for (TodoMoveRequestDto request : todoRequestDto.getDtoList()) {

            for (Todo todo : todoList) {
                if (todo.getId().equals(request.getTodoId())) {
                    todo.updateNumber(request.getNumber());
                }
            }

        }
    }
}
