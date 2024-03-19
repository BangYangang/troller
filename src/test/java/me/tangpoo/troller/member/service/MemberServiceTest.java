package me.tangpoo.troller.member.service;

import static org.mockito.BDDMockito.given;

import java.util.Optional;
import me.tangpoo.troller.domain.member.dto.MemberRequest;
import me.tangpoo.troller.domain.member.entity.Member;
import me.tangpoo.troller.domain.member.repository.MemberRepository;
import me.tangpoo.troller.domain.member.service.MemberService;
import me.tangpoo.troller.member.common.MemberTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest implements MemberTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    void 회원가입은_중복된_유저이름을_허용하지_않는다() {
        // given
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setUsername(TEST_USER_NAME);
        memberRequest.setEmail(TEST_USER_EMAIL);
        memberRequest.setPassword(TEST_USER_PASSWORD);

        given(memberRepository.findByUsername(TEST_USER_NAME)).willReturn(Optional.of(TEST_MEMBER));

        // when + then
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> memberService.saveMember(memberRequest));
    }
}
