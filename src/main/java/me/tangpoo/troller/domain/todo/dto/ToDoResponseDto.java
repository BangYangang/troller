package me.tangpoo.troller.domain.todo.dto;

import lombok.Getter;
import me.tangpoo.troller.domain.todo.entity.Todo;

@Getter
public class ToDoResponseDto {

    private String todoName;

    public ToDoResponseDto(Todo todo) {
        this.todoName = todo.getTodoName();
    }

}
