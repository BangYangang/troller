package me.tangpoo.troller.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.comment.dto.CommentForm;
import me.tangpoo.troller.domain.comment.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards/{boardId}/todos/{todoId}/cards/{cardId}/comments")
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public ResponseEntity<String> createComment(
      @AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long boardId,
      @PathVariable Long todoId,
      @PathVariable Long cardId,
      @RequestBody @Validated CommentForm dto
  ) {
    return ResponseEntity.status(201)
        .body(commentService.create(userDetails.getUsername(), boardId, todoId, cardId, dto));
  }

  @PutMapping("/{commentId}")
  public ResponseEntity<String> updateComment(
      @AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long boardId,
      @PathVariable Long todoId,
      @PathVariable Long cardId,
      @PathVariable Long commentId,
      @RequestBody @Validated CommentForm dto
  ) {
    return ResponseEntity.status(201)
        .body(
            commentService.update(userDetails.getUsername(), boardId, todoId, cardId, commentId,
                dto));
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<String> deleteComment(
      @AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long boardId,
      @PathVariable Long todoId,
      @PathVariable Long cardId,
      @PathVariable Long commentId
  ) {
    return ResponseEntity.status(201)
        .body(commentService.delete(userDetails.getUsername(), boardId, todoId, cardId,
            commentId));
  }
}
