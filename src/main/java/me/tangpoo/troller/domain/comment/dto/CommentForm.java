package me.tangpoo.troller.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentForm {

  @NotBlank(message = "내용을 입력해야 합니다.")
  private String content;
}
