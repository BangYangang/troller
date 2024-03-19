package me.tangpoo.troller.domain.card.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CreateCardForm {

  @NotBlank
  private String name;

  @NotBlank
  private String description;
}
