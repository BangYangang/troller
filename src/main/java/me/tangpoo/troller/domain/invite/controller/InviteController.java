package me.tangpoo.troller.domain.invite.controller;

import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.board.dto.BoardRequestDto;
import me.tangpoo.troller.domain.invite.dto.InviteRequestDto;
import me.tangpoo.troller.domain.invite.service.InviteService;
import me.tangpoo.troller.global.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class InviteController {
    private final InviteService inviteService;

    @PostMapping("/{board_id}/invite")
    public ResponseEntity<String> createBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody InviteRequestDto requestDto, @PathVariable Long board_id){
        inviteService.invite(board_id, requestDto, userDetails.getMember());
        return new ResponseEntity<>("초대 완료", HttpStatus.CREATED);
    }
}
