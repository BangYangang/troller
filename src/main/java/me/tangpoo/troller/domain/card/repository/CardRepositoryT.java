package me.tangpoo.troller.domain.card.repository;

import java.util.List;
import me.tangpoo.troller.domain.card.entity.Card;
import me.tangpoo.troller.domain.todo.entity.Todo;

public interface CardRepositoryT {

    int countCardsByTodo(Todo todo);

    List<Card> findAllByTodo(Todo todo);

    List<Card> findAllByTodoIn(List<Todo> todos);
}
