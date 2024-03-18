package me.tangpoo.troller.domain.member.controller;

import static me.tangpoo.troller.global.JwtUtil.AUTHORIZATION_HEADER;
import static me.tangpoo.troller.global.JwtUtil.REFRESH_TOKEN;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.member.dto.LoginRequest;
import me.tangpoo.troller.domain.member.dto.TokenDto;
import me.tangpoo.troller.domain.member.service.AuthService;
import me.tangpoo.troller.global.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest){
        TokenDto token = authService.login(loginRequest);

        return ResponseEntity.ok()
            .header(AUTHORIZATION_HEADER, token.getAccessToken())
            .header(REFRESH_TOKEN, token.getRefreshToken())
            .body("로그인 성공");
    }

    @PostMapping("/signout")
    public ResponseEntity<String> logout(
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestHeader(REFRESH_TOKEN) String refreshToken
    ){
        authService.logout(userDetails.getMember(), refreshToken);

        return ResponseEntity.ok()
            .header(AUTHORIZATION_HEADER, (String) null)
            .body("로그아웃 성공");
    }
}
