package me.tangpoo.troller.domain.board.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.card.entity.Card;
import me.tangpoo.troller.domain.todo.entity.Todo;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private String title;
    private String description;
    private String color;
    private List<TodoResponse> todos;
    private List<CardResponse> cards;

    public BoardResponseDto(Board board, List<TodoResponse> todos, List<CardResponse> cards) {
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.color = board.getColor();
        this.todos = todos;
        this.cards = cards;
    }
}
