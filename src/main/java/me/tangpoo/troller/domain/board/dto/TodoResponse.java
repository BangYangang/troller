package me.tangpoo.troller.domain.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.tangpoo.troller.domain.todo.entity.Todo;

@Getter
@NoArgsConstructor
public class TodoResponse {

    private Long todoId;
    private Long todoOrder;
    private String todoName;


    public TodoResponse(Todo todo) {
        this.todoId = todo.getId();
        this.todoName = todo.getTodoName();
        this.todoOrder = todo.getTodoOrder();
    }
}
