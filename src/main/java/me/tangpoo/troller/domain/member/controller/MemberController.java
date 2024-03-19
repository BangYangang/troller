package me.tangpoo.troller.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.member.dto.MemberResponse;
import me.tangpoo.troller.domain.member.dto.MemberRequest;
import me.tangpoo.troller.domain.member.service.MemberService;
import me.tangpoo.troller.global.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> saveMember(@Valid @RequestBody MemberRequest memberRequest) {
        memberService.saveMember(memberRequest);

        return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<MemberResponse> getMember(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ResponseEntity<>(memberService.getMember(userDetails.getMember().getMemberId()),
            HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateMember(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @Valid @RequestBody MemberRequest memberRequest) {
        memberService.updateMember(userDetails.getMember().getMemberId(), memberRequest);

        return new ResponseEntity<>("유저 정보 수정 성공", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteMember(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        memberService.delete(userDetails.getMember().getMemberId());

        return new ResponseEntity<>("회원탈퇴 성공", HttpStatus.NO_CONTENT);
    }
}
