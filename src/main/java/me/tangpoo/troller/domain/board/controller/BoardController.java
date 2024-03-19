package me.tangpoo.troller.domain.board.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.board.dto.BoardRequestDto;
import me.tangpoo.troller.domain.board.dto.BoardResponseDto;
import me.tangpoo.troller.domain.board.service.BoardService;
import me.tangpoo.troller.global.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<String> createBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody BoardRequestDto requestDto){
        boardService.createBoard(requestDto, userDetails.getMember());
        return new ResponseEntity<>("보드 생성 완료", HttpStatus.CREATED);
    }

    @GetMapping
    public List<BoardResponseDto> readAllBoard(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return boardService.readAllBoard(userDetails.getMember());
    }

    @GetMapping("/{board_id}")
    public BoardResponseDto readBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long board_id){
        return boardService.readBoard(userDetails.getMember(), board_id);
    }


    @PutMapping("/{board_id}")
    public ResponseEntity<String> updateBoard(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long board_id, @RequestBody BoardRequestDto requestDto){
        boardService.updateBoard(userDetails.getMember(), requestDto, board_id);
        return new ResponseEntity<>("보드 수정 완료", HttpStatus.OK);
    }

    @DeleteMapping("/{board_id}")
    public ResponseEntity<String> deleteBoard(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long board_id){
        boardService.deleteBoard(userDetails.getMember(), board_id);
        return new ResponseEntity<>("보드 삭제 완료", HttpStatus.NO_CONTENT);
    }

}
