package me.tangpoo.troller.domain.member.service;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.member.dto.LoginRequest;
import me.tangpoo.troller.domain.member.dto.TokenDto;
import me.tangpoo.troller.domain.member.entity.Member;
import me.tangpoo.troller.domain.member.entity.RefreshToken;
import me.tangpoo.troller.domain.member.repository.MemberRepository;
import me.tangpoo.troller.domain.member.repository.RefreshTokenRepository;
import me.tangpoo.troller.global.JwtUtil;
import me.tangpoo.troller.global.exception.UnAuthorizationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;


    public TokenDto login(LoginRequest loginRequest) {
        Member member = findMemberByUsername(loginRequest.getUsername());

        if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new AccessDeniedException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtUtil.createToken(member);
        String refreshToken = jwtUtil.createRefreshToken(member);
        RefreshToken tokenEntity = RefreshToken.builder()
            .refreshToken(refreshToken)
            .member(member)
            .build();

        refreshTokenRepository.findByMember(member).ifPresentOrElse(
            (findTokenPair) -> findTokenPair.updateToken(refreshToken),
            () -> refreshTokenRepository.save(tokenEntity)
        );

        return new TokenDto(accessToken, refreshToken);
    }

    public void logout(Member member, String refreshToken) {
        if (!jwtUtil.validateToken(refreshToken)) {
            throw new UnAuthorizationException("[ERROR] 유효하지 않은 Refresh Token 입니다.");
        }

        final String tokenUsername = jwtUtil.getClaimsFormRefreshToken(refreshToken).getSubject();
        final String entityUsername = memberRepository.findById(member.getMemberId()).orElseThrow(
            () -> new NoSuchElementException("유저 정보가 일치하지 않습니다.")
        ).getUsername();

        if(!tokenUsername.equals(entityUsername)){
            throw new UnAuthorizationException("[ERROR] 로그인한 사용자의 Refresh Token 이 아닙니다.");
        }

        RefreshToken findRefreshToken = refreshTokenRepository.findByMember(member).orElseThrow(
            () -> new UnAuthorizationException("[ERROR] 이미 로그아웃 된 사용자입니다.")
        );

        refreshTokenRepository.delete(findRefreshToken);
    }

    private Member findMemberByUsername(String username) {
        return memberRepository.findByUsername(username).orElseThrow(
                () -> new NoSuchElementException("유저 정보가 일치하지 않습니다.")
            );
    }
}
