package me.tangpoo.troller.domain.card.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ResponseCardForm {

  private String name;
  private String description;
  private String color;
  private LocalDateTime deadline;
}
