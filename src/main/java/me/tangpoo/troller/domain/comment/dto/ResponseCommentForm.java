package me.tangpoo.troller.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCommentForm {

  private String username;
  private String content;
}
