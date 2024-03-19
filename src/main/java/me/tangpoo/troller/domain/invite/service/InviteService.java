package me.tangpoo.troller.domain.invite.service;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.board.repository.BoardRepository;
import me.tangpoo.troller.domain.invite.dto.InviteRequestDto;
import me.tangpoo.troller.domain.invite.entity.Invite;
import me.tangpoo.troller.domain.invite.repository.InviteRepository;
import me.tangpoo.troller.domain.member.entity.Member;
import me.tangpoo.troller.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InviteService {

    private final BoardRepository boardRepository;
    private final InviteRepository inviteRepository;
    private final MemberRepository memberRepository;
    public void invite(Long boardId, InviteRequestDto requestDto, Member member) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("보드를 찾을 수 없습니다."));
        Member master = memberRepository.findById(member.getMemberId()).orElseThrow(() -> new NoSuchElementException("유저를 찾을 수 없습니다."));
        if(!board.getMember().equals(master)){
            throw new IllegalArgumentException("본인의 보드가 아닙니다.");
        }
        Member inviteMember = memberRepository.findByUsername(requestDto.getUsername()).orElseThrow(() -> new NoSuchElementException("유저를 찾을 수 없습니다."));
        inviteRepository.save(new Invite(inviteMember, board));
    }
}
