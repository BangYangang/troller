package me.tangpoo.troller.domain.todo.repository;

import java.util.List;
import me.tangpoo.troller.domain.todo.entity.Todo;

public interface TodoRepositoryT {

    List<Todo> findByBoard_BoardId(Long boardId);

}
