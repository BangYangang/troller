package me.tangpoo.troller.domain.board.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.board.dto.BoardRequestDto;
import me.tangpoo.troller.domain.board.dto.BoardResponseDto;
import me.tangpoo.troller.domain.board.dto.BoardsResponseDto;
import me.tangpoo.troller.domain.board.dto.CardResponse;
import me.tangpoo.troller.domain.board.dto.TodoResponse;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.board.repository.BoardRepository;
import me.tangpoo.troller.domain.card.entity.Card;
import me.tangpoo.troller.domain.card.repository.CardRepository;
import me.tangpoo.troller.domain.invite.entity.Invite;
import me.tangpoo.troller.domain.invite.repository.InviteRepository;
import me.tangpoo.troller.domain.member.entity.Member;
import me.tangpoo.troller.domain.member.repository.MemberRepository;
import me.tangpoo.troller.domain.todo.entity.Todo;
import me.tangpoo.troller.domain.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final InviteRepository inviteRepository;
    private final TodoRepository todoRepository;
    private final CardRepository cardRepository;


    public void createBoard(BoardRequestDto requestDto, Member member) {
        boardRepository.save(new Board(requestDto, member));
    }

    public BoardResponseDto readBoard(Member member, Long boardId) {
        //검증 단계
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new NoSuchElementException("보드가 존재하지 않습니다."));
        Member user = memberRepository.findById(member.getMemberId())
            .orElseThrow(() -> new NoSuchElementException("가입되어 있지 않습니다."));
        Invite invite = inviteRepository.findByBoard_BoardIdAndMember_MemberId(board.getBoardId(),
            user.getMemberId());
        if (!board.getMember().equals(user) && !invite.getMember().equals(user)) {
            throw new IllegalArgumentException("본인의 보드가 아닙니다.");
        }
        //정보 담기
        List<Todo> todos = todoRepository.findByBoard_BoardId(boardId);
        List<TodoResponse> todosR = todos.stream().map(TodoResponse::new).toList();
        List<Card> cards = cardRepository.findAllByTodoIn(todos);
        List<CardResponse> cardsR = cards.stream().map(CardResponse::new).toList();

        return new BoardResponseDto(board, todosR, cardsR);
    }

    @Transactional
    public void updateBoard(Member member, BoardRequestDto requestDto, Long boardId) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new NoSuchElementException("보드가 존재하지 않습니다."));
        Member master = memberRepository.findById(member.getMemberId())
            .orElseThrow(() -> new NoSuchElementException("멤버를 찾을 수 없습니다."));
        if (!board.getMember().equals(master)) {
            throw new IllegalArgumentException("본인의 보드가 아닙니다.");
        }
        board.update(requestDto);
    }

    public void deleteBoard(Member member, Long board_id) {
        Board board = boardRepository.findById(board_id)
            .orElseThrow(() -> new NoSuchElementException("보드가 존재하지 않습니다."));
        Member master = memberRepository.findById(member.getMemberId())
            .orElseThrow(() -> new NoSuchElementException("멤버를 찾을 수 없습니다."));
        if (!board.getMember().equals(master)) {
            throw new IllegalArgumentException("본인의 보드가 아닙니다.");
        }
        boardRepository.delete(board);
    }

    public List<BoardsResponseDto> readAllBoard(Member member) {
        Member register = memberRepository.findById(member.getMemberId())
            .orElseThrow(() -> new NoSuchElementException("멤버를 찾을 수 없습니다."));
        List<Board> ownBoards = boardRepository.findAllByMember(register);
        List<Invite> invites = inviteRepository.findAllByMember(register);
        List<BoardsResponseDto> boards = new ArrayList<>(
            ownBoards.stream().map(BoardsResponseDto::new).toList());
        boards.addAll(invites.stream().map(invite -> new BoardsResponseDto(invite.getBoard()))
            .toList());

        return boards;
    }
}
