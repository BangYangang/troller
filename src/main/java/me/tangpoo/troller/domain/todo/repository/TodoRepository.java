package me.tangpoo.troller.domain.todo.repository;

import me.tangpoo.troller.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo,Long> {
    List<Todo> findByBoard_BoardId(Long boardId);
}
