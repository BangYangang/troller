package me.tangpoo.troller.domain.todo.dto;

import lombok.Getter;
import me.tangpoo.troller.domain.todo.entity.Todo;

@Getter
public class ToDoResponse {

    private String todoName;

    public ToDoResponse(Todo todo) {
        this.todoName = todo.getTodoName();
    }

}
