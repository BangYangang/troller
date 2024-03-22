package me.tangpoo.troller.domain.pic.controller;

import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.pic.dto.PicRequestDto;
import me.tangpoo.troller.domain.pic.service.PicService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards/{boardId}/cards/{cardId}")
public class PicController {

  private final PicService picService;

  @PostMapping()
  public ResponseEntity<String> createPic(
      @PathVariable Long boardId,
      @PathVariable Long cardId,
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestBody PicRequestDto dto
  ){

    return ResponseEntity.ok().body(picService.join(boardId, cardId, userDetails.getUsername(), dto));
  }
}
