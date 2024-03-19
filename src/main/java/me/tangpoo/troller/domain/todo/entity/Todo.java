package me.tangpoo.troller.domain.todo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.todo.dto.TodoRequestDto;

@Entity
@Getter
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    private String todoName;

    private Long todoOrder;

    public boolean isNotBoardMatch(Board board){
        return !this.board.equals(board);
    }

    public Todo(Board board, String todoName) {
        this.board = board;
        this.todoName = todoName;
    }

    public void update(TodoRequestDto todoRequestDto) {
        this.todoName = todoRequestDto.getTodoName();
    }

    public void updateNumber(Long number) {
        this.todoOrder = number;
    }
}
