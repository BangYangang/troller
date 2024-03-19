package me.tangpoo.troller.domain.board.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.card.entity.Card;
import me.tangpoo.troller.domain.todo.entity.Todo;

@Getter
@NoArgsConstructor
public class BoardsResponseDto {

    private String title;
    private String description;
    private String color;


    public BoardsResponseDto(Board board) {
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.color = board.getColor();
    }
}
