package me.tangpoo.troller.domain.card.repository;

import static me.tangpoo.troller.domain.card.entity.QCard.card;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import me.tangpoo.troller.domain.card.entity.Card;
import me.tangpoo.troller.domain.todo.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class CardRepositoryImpl extends QuerydslRepositorySupport implements CardRepositoryT {

    @Autowired
    private JPAQueryFactory queryFactory;

    public CardRepositoryImpl() {
        super(Card.class);
    }

    @Override
    public int countCardsByTodo(Todo todo) {
        return (int) queryFactory.selectFrom(card)
            .where(card.todo.eq(todo))
            .fetchCount();
    }

    @Override
    public List<Card> findAllByTodo(Todo todo) {
        return queryFactory.selectFrom(card)
            .where(card.todo.eq(todo))
            .fetch();
    }

    @Override
    public List<Card> findAllByTodoIn(List<Todo> todos) {
        return queryFactory.selectFrom(card)
            .where(card.todo.in(todos))
            .fetch();
    }

}
