package me.tangpoo.troller.domain.todo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoMoveRequestDto {

    private Long todoId;
    private Long number;


    private List<TodoMoveRequestDto> dtoList;

}
