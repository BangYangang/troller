package me.tangpoo.troller.domain.todo.repository;

import me.tangpoo.troller.domain.todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
