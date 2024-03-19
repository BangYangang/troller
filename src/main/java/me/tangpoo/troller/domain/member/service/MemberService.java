package me.tangpoo.troller.domain.member.service;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.member.dto.MemberResponse;
import me.tangpoo.troller.domain.member.dto.MemberRequest;
import me.tangpoo.troller.domain.member.entity.Member;
import me.tangpoo.troller.domain.member.repository.MemberRepository;
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
        Member findMember = getFindMember(memberId);

        return new MemberResponse(findMember.getUsername(), findMember.getUsername());
    }



    @Transactional
    public void updateMember(Long memberId, MemberRequest memberRequest) {
        Member findMember = getFindMember(memberId);

        findMember.update(memberRequest.getUsername(),
            memberRequest.getEmail(),
            memberRequest.getPassword()
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
