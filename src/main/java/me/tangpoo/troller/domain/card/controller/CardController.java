package me.tangpoo.troller.domain.card.controller;


import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.card.dto.CreateCardForm;
import me.tangpoo.troller.domain.card.dto.ResponseCardDetailForm;
import me.tangpoo.troller.domain.card.dto.ResponseCardForm;
import me.tangpoo.troller.domain.card.dto.UpdateCardForm;
import me.tangpoo.troller.domain.card.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards/{boardId}/todos/{todoId}/cards")
public class CardController {

  private final CardService cardService;

  @PostMapping
  public ResponseEntity<String> createCard(
      @AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long boardId,
      @PathVariable Long todoId,
      @Validated @RequestBody CreateCardForm dto
  ) {
    return ResponseEntity.status(201)
        .body(cardService.create(boardId, todoId, dto, userDetails.getUsername()));
  }

  @GetMapping("/{cardId}")
  public ResponseEntity<ResponseCardDetailForm> getCard(
      @AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long boardId,
      @PathVariable Long todoId,
      @PathVariable Long cardId
  ) {

    return ResponseEntity.status(201)
        .body(cardService.get(boardId, todoId, cardId, userDetails.getUsername()));
  }

  @PutMapping("/{cardId}")
  public ResponseEntity<ResponseCardForm> updateCard(
      @AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long boardId,
      @PathVariable Long cardId,
      @PathVariable Long todoId,
      @Validated @RequestBody UpdateCardForm dto
  ){
    return ResponseEntity.status(201)
        .body(cardService.update(boardId, cardId, todoId, dto, userDetails.getUsername()));
  }

  @DeleteMapping("/{cardId}")
  public ResponseEntity<String> delete(
      @AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long boardId,
      @PathVariable Long todoId,
      @PathVariable Long cardId
  ){
    return ResponseEntity.status(201)
        .body(cardService.delete(boardId, todoId, cardId, userDetails.getUsername()));
  }
}
