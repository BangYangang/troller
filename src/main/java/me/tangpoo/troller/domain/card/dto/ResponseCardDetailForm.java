package me.tangpoo.troller.domain.card.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.tangpoo.troller.domain.comment.dto.ResponseCommentForm;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCardDetailForm {

  private String name;

  private String description;

  private String color;

  private LocalDateTime deadline;

  private List<ResponseCommentForm> comments;

}
