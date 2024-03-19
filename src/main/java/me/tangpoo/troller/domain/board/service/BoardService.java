package me.tangpoo.troller.domain.board.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.board.dto.BoardRequestDto;
import me.tangpoo.troller.domain.board.dto.BoardResponseDto;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.board.repository.BoardRepository;
import me.tangpoo.troller.domain.invite.entity.Invite;
import me.tangpoo.troller.domain.invite.repository.InviteRepository;
import me.tangpoo.troller.domain.member.entity.Member;
import me.tangpoo.troller.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final InviteRepository inviteRepository;


    public void createBoard(BoardRequestDto requestDto, Member member) {
        boardRepository.save(new Board(requestDto, member));
    }

    public BoardResponseDto readBoard(Member member, Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("보드가 존재하지 않습니다."));
        Member master = memberRepository.findById(member.getMemberId()).orElseThrow(() -> new NoSuchElementException("가입되어 있지 않습니다."));
        if(!(board.getMember()).equals(master)){
            throw new IllegalArgumentException("본인의 보드가 아닙니다.");
        }
        return new BoardResponseDto(board);
    }
    @Transactional
    public void updateBoard(Member member, BoardRequestDto requestDto, Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("보드가 존재하지 않습니다."));
        Member master = memberRepository.findById(member.getMemberId()).orElseThrow(() -> new NoSuchElementException("멤버를 찾을 수 없습니다."));
        if(!board.getMember().equals(master)){
            throw new IllegalArgumentException("본인의 보드가 아닙니다.");
        }
        board.update(requestDto);
    }

    public void deleteBoard(Member member, Long board_id){
        Board board = boardRepository.findById(board_id).orElseThrow(() -> new NoSuchElementException("보드가 존재하지 않습니다."));
        Member master = memberRepository.findById(member.getMemberId()).orElseThrow(() -> new NoSuchElementException("멤버를 찾을 수 없습니다."));
        if (!board.getMember().equals(master)){
            throw new IllegalArgumentException("본인의 보드가 아닙니다.");
        }
        boardRepository.delete(board);
    }

    public List<BoardResponseDto> readAllBoard(Member member) {
        Member register = memberRepository.findById(member.getMemberId()).orElseThrow(() -> new NoSuchElementException("멤버를 찾을 수 없습니다."));
        List<BoardResponseDto> boards = new ArrayList<>();
        List<Board> ownBoards = boardRepository.findAllByMember(register);
        List<Invite> invites = inviteRepository.findAllByMember(register);
        for(Board board : ownBoards){
            boards.add((new BoardResponseDto(board)));
        }
        for (Invite invite : invites) {
            boards.add(new BoardResponseDto(invite.getBoard()));
        }

        return boards;
    }
}
