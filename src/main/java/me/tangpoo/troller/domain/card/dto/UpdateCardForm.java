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
public class UpdateCardForm {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String color;

    private LocalDateTime deadline;
}
