package me.tangpoo.troller.domain.card.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.tangpoo.troller.domain.card.dto.ResponseCardDetailForm;
import me.tangpoo.troller.domain.card.dto.ResponseCardForm;
import me.tangpoo.troller.domain.card.dto.UpdateCardForm;
import me.tangpoo.troller.domain.comment.dto.ResponseCommentForm;
import me.tangpoo.troller.domain.todo.entity.Todo;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cards")
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cards_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "todo_id")
  private Todo todo;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String color;

  @Column(nullable = false)
  private int cardOrder;

  @Column(nullable = false)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime deadline;

    public ResponseCardForm createCardResponseDto() {
        return ResponseCardForm.builder()
            .name(this.name)
            .description(this.description)
            .color(this.color)
            .deadline(this.deadline)
            .build();
    }

    public ResponseCardDetailForm createCardDetailForm(List<ResponseCommentForm> comments) {
        return ResponseCardDetailForm.builder()
            .name(this.name)
            .description(this.description)
            .color(this.color)
            .deadline(this.deadline)
            .comments(comments)
            .build();
    }

    public void update(UpdateCardForm dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.color = dto.getColor();
        this.deadline = dto.getDeadline() == null ? this.deadline : dto.getDeadline();
    }

    public boolean isNotTodoMatch(Todo todo) {
        return !this.todo.equals(todo);
    }


  public void move(Todo nextTodo) {
      this.todo = nextTodo;
  }
}
