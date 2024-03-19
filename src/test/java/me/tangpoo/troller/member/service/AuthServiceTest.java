package me.tangpoo.troller.member.service;

import static me.tangpoo.troller.member.common.MemberTest.TEST_MEMBER;
import static me.tangpoo.troller.member.common.MemberTest.TEST_REFRESH_TOKEN;
import static me.tangpoo.troller.member.common.MemberTest.TEST_REFRESH_TOKEN_VALUE;
import static me.tangpoo.troller.member.common.MemberTest.TEST_TOKEN;
import static me.tangpoo.troller.member.common.MemberTest.TEST_USER_ID;
import static me.tangpoo.troller.member.common.MemberTest.TEST_USER_NAME;
import static me.tangpoo.troller.member.common.MemberTest.TEST_USER_PASSWORD;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.NoSuchElementException;
import java.util.Optional;
import me.tangpoo.troller.domain.member.dto.LoginRequest;
import me.tangpoo.troller.domain.member.repository.MemberRepository;
import me.tangpoo.troller.domain.member.repository.RefreshTokenRepository;
import me.tangpoo.troller.domain.member.service.AuthService;
import me.tangpoo.troller.global.JwtUtil;
import me.tangpoo.troller.global.exception.UnAuthorizationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtUtil jwtUtil;

    @Mock
    RefreshTokenRepository refreshTokenRepository;

    @Nested
    class 유저_로그인{
        LoginRequest loginRequest = new LoginRequest(TEST_USER_NAME, TEST_USER_PASSWORD);

        @Test
        void 유저_이름이_일치하지_않다면_에러(){
            // given
            given(memberRepository.findByUsername(loginRequest.getUsername())).willReturn(Optional.empty());

            // when + then
            assertThrows(NoSuchElementException.class, () -> authService.login(loginRequest));
        }

        @Test
        void 비밀번호가_일치하지_않다면_에러(){
            // given
            given(memberRepository.findByUsername(loginRequest.getUsername())).willReturn(Optional.of(TEST_MEMBER));
            given(passwordEncoder.matches(anyString(), anyString())).willReturn(false);
            // when + then
            assertThrows(AccessDeniedException.class, () -> authService.login(loginRequest));
        }
    }

    @Nested
    class 유저_로그아웃{
        @Test
        void 토큰이_유효하지_않으면_에러(){
            // given
            given(jwtUtil.validateToken(TEST_REFRESH_TOKEN_VALUE)).willReturn(false);

            // when + then
            assertThrows(UnAuthorizationException.class, () -> authService.logout(TEST_MEMBER, TEST_REFRESH_TOKEN_VALUE));
        }

    }
}
