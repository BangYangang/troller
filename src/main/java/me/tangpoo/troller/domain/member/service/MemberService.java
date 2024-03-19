package me.tangpoo.troller.domain.member.service;

import static com.querydsl.core.types.Projections.fields;
import static me.tangpoo.troller.domain.member.entity.QMember.member;

import com.querydsl.core.QueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.member.dto.MemberResponse;
import me.tangpoo.troller.domain.member.dto.MemberRequest;
import me.tangpoo.troller.domain.member.entity.Member;
import me.tangpoo.troller.domain.member.entity.RefreshToken;
import me.tangpoo.troller.domain.member.repository.MemberRepository;
import me.tangpoo.troller.domain.member.repository.RefreshTokenRepository;
import org.springframework.dao.DataIntegrityViolationException;
import me.tangpoo.troller.domain.member.repository.RefreshTokenRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JPAQueryFactory queryFactory;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveMember(MemberRequest memberRequest){
        validateDuplicateUsername(memberRequest.getUsername());

        Member member = Member.builder()
            .username(memberRequest.getUsername())
            .email(memberRequest.getEmail())
            .password(passwordEncoder.encode(memberRequest.getPassword()))
            .build();

        memberRepository.save(member);
    }

    private void validateDuplicateUsername(String username) {
        if(memberRepository.findByUsername(username).isPresent()){
            throw new DataIntegrityViolationException("이미 존재하는 회원명 입니다.");
        }
    }

    public MemberResponse getMember(Long memberId) {
        return queryFactory.select(fields(MemberResponse.class,
            member.username,
            member.email))
            .from(member)
            .where(member.memberId.eq(memberId))
            .fetchOne();
    }


    @Transactional
    public void updateMember(Long memberId, MemberRequest memberRequest) {
        Member findMember = getFindMember(memberId);

        findMember.update(
            memberRequest.getUsername(),
            memberRequest.getEmail(),
            passwordEncoder.encode(memberRequest.getPassword())
        );
    }

    @Transactional
    public void delete(Long memberId) {
        Member findMember = getFindMember(memberId);

        memberRepository.delete(findMember);
    }

    private Member getFindMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
            () -> new NoSuchElementException("회원 정보를 찾지 못했습니다.")
        );
    }
}
