package me.tangpoo.troller.domain.board.dto;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.tangpoo.troller.domain.board.entity.Board;

@Getter
@NoArgsConstructor
public class BoardResponseDto {

    private String title;
    private String description;
    private String color;
    public BoardResponseDto(Board board) {
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.color = board.getColor();
    }
}
