package me.tangpoo.troller.domain.todo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.todo.dto.TodoMoveRequestDto;
import me.tangpoo.troller.domain.todo.entity.QTodo;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TodoQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Lock(LockModeType.OPTIMISTIC)
    public void updateOrder(TodoMoveRequestDto request, Long boardId) {
        QTodo qTodo = new QTodo("c");
        jpaQueryFactory.update(qTodo).set(qTodo.todoOrder, request.getNumber())
            .where(qTodo.board.boardId.eq(boardId), qTodo.id.eq(
                request.getTodoId())).execute();

    }
}
