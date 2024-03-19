package me.tangpoo.troller.domain.card.repository;

import static org.junit.jupiter.api.Assertions.*;

import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.board.repository.BoardRepository;
import me.tangpoo.troller.domain.todo.entity.Todo;
import me.tangpoo.troller.domain.todo.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CardRepositoryTest {

  @Autowired
  CardRepository cardRepository;

  @Autowired
  TodoRepository todoRepository;

  @Autowired
  BoardRepository boardRepository;
  @Test
  @Transactional
  public void 카드_갯수_조회(){
    Board save1 = boardRepository.save(new Board("test", "test", "test"));

    Todo todo = new Todo(save1, "test", "test");

    Todo save = todoRepository.save(todo);

    System.out.println(cardRepository.countCardsByTodo(save));
  }
}