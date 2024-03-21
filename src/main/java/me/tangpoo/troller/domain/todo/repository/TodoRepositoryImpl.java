package me.tangpoo.troller.domain.todo.repository;

import static me.tangpoo.troller.domain.todo.entity.QTodo.todo;

import java.util.List;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.tangpoo.troller.domain.todo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class TodoRepositoryImpl extends QuerydslRepositorySupport implements TodoRepositoryT {

    @Autowired
    private JPAQueryFactory queryFactory;

    public TodoRepositoryImpl() {
        super(Todo.class);
    }

    @Override
    public List<Todo> findByBoard_BoardId(Long boardId) {
        return queryFactory.selectFrom(todo)
            .where(todo.board.boardId.eq(boardId))
            .fetch();
    }

}
