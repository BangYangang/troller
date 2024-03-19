package me.tangpoo.troller.domain.card.repository;

import me.tangpoo.troller.domain.card.entity.Card;
import me.tangpoo.troller.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

  int countCardsByTodo(Todo todo);
}
