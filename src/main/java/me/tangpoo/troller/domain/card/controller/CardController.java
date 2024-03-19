package me.tangpoo.troller.domain.card.controller;


import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.card.dto.CreateCardForm;
import me.tangpoo.troller.domain.card.dto.ResponseCardForm;
import me.tangpoo.troller.domain.card.dto.UpdateCardForm;
import me.tangpoo.troller.domain.card.service.CardService;
import org.springframework.http.ResponseEntity;
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
      @PathVariable Long boardId,
      @PathVariable Long todoId,
      @RequestBody CreateCardForm dto
  ) {
    return ResponseEntity.status(201)
        .body(cardService.create(boardId, todoId, dto));
  }

  @GetMapping("/{cardId}")
  public ResponseEntity<ResponseCardForm> getCard(
      @PathVariable Long boardId,
      @PathVariable Long todoId,
      @PathVariable Long cardId
  ) {

    return ResponseEntity.status(201)
        .body(cardService.get(boardId, todoId, cardId));
  }

  @PutMapping("/{cardId}")
  public ResponseEntity<ResponseCardForm> updateCard(
      @PathVariable Long boardId,
      @PathVariable Long cardId,
      @PathVariable Long todoId,
      @RequestBody UpdateCardForm dto
  ){
    return ResponseEntity.status(201)
        .body(cardService.update(boardId, cardId, todoId, dto));
  }

  @DeleteMapping("/{cardId}")
  public ResponseEntity<String> delete(
      @PathVariable Long boardId,
      @PathVariable Long todoId,
      @PathVariable Long cardId
  ){
    return ResponseEntity.status(201)
        .body(cardService.delete(boardId, todoId, cardId));
  }
}
