package me.tangpoo.troller.domain.board.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.tangpoo.troller.domain.card.entity.Card;
import me.tangpoo.troller.domain.todo.entity.Todo;

@Getter
@NoArgsConstructor
public class CardResponse {

    private Long cardId;
    private Long todoId;
    private int cardOrder;
    private String name;
    private String description;
    private String color;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime deadLine;


    public CardResponse(Card card) {
        this.cardId = card.getId();
        this.cardOrder = card.getCardOrder();
        this.color = card.getColor();
        this.deadLine = card.getDeadline();
        this.description = card.getDescription();
        this.name = card.getName();
        this.todoId = card.getTodo().getId();
    }

}
