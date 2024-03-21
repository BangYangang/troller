package me.tangpoo.troller.domain.todo.repository;

import java.util.List;
import me.tangpoo.troller.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByBoard_BoardId(Long boardId);
}
