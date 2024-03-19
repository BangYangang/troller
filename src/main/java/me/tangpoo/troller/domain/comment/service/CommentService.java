package me.tangpoo.troller.domain.comment.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.board.repository.BoardRepository;
import me.tangpoo.troller.domain.card.entity.Card;
import me.tangpoo.troller.domain.card.repository.CardRepository;
import me.tangpoo.troller.domain.comment.dto.CommentForm;
import me.tangpoo.troller.domain.comment.entity.Comment;
import me.tangpoo.troller.domain.comment.repository.CommentRepository;
import me.tangpoo.troller.domain.invite.repository.InviteRepository;
import me.tangpoo.troller.domain.member.entity.Member;
import me.tangpoo.troller.domain.member.repository.MemberRepository;
import me.tangpoo.troller.domain.todo.entity.Todo;
import me.tangpoo.troller.domain.todo.repository.TodoRepository;
import me.tangpoo.troller.global.exception.custom.EntityNotMatchException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

  private final BoardRepository boardRepository;
  private final TodoRepository todoRepository;
  private final CommentRepository commentRepository;
  private final CardRepository cardRepository;
  private final MemberRepository memberRepository;
  private final InviteRepository inviteRepository;

  public String create(String username, Long boardId, Long todoId, Long cardId, CommentForm dto) {
    Member member = findMemberBy(username);
    Board board = findBoardBy(boardId);
    Todo todo = findTodoBy(todoId);
    Card card = findCardBy(cardId);

    inviteCheckValidate(boardId, member);
    boardMatchValidate(todo, board);
    todoMatchValidate(card, todo);

    commentRepository.save(
        Comment.builder().
            member(member)
            .card(card)
            .content(dto.getContent())
            .build()
    );

    return "댓글 작성 성공하였습니다.";
  }


  public String update(String username, Long boardId, Long todoId, Long cardId, Long commentId,
      CommentForm dto) {
    Member member = findMemberBy(username);
    Board board = findBoardBy(boardId);
    Todo todo = findTodoBy(todoId);
    Card card = findCardBy(cardId);

    inviteCheckValidate(boardId, member);
    boardMatchValidate(todo, board);
    todoMatchValidate(card, todo);

    Comment comment = findCommentBy(commentId);

    comment.update(dto);

    return "댓글 수정을 성공하였습니다.";
  }


  public String delete(String username, Long boardId, Long todoId, Long cardId, Long commentId) {
    Member member = findMemberBy(username);
    Board board = findBoardBy(boardId);
    Todo todo = findTodoBy(todoId);
    Card card = findCardBy(cardId);

    inviteCheckValidate(boardId, member);
    boardMatchValidate(todo, board);
    todoMatchValidate(card, todo);

    Comment comment = findCommentBy(commentId);
    commentRepository.delete(comment);

    return "댓글 삭제를 성공하였습니다!";
  }

  private Card findCardBy(Long cardId) {
    return cardRepository.findById(cardId).orElseThrow(
        () -> new EntityNotFoundException("해당 카드를 찾을 수 없습니다.")
    );
  }

  private Todo findTodoBy(Long todoId) {
    return todoRepository.findById(todoId).orElseThrow(
        () -> new EntityNotFoundException("해당 컬럼을 찾을 수 없습니다.")
    );
  }

  private Board findBoardBy(Long boardId) {
    return boardRepository.findById(boardId).orElseThrow(
        () -> new EntityNotFoundException("해당 보드를 찾을 수 없습니다.")
    );
  }

  private Member findMemberBy(String username) {
    return memberRepository.findByUsername(username).orElseThrow(
        () -> new EntityNotFoundException("해당 유저를 찾을 수 없습니다.")
    );
  }

  private Comment findCommentBy(Long commentId) {
    return commentRepository.findById(commentId).orElseThrow(
        () -> new EntityNotFoundException("해당 댓글을 찾을 수 없습니다.")
    );
  }


  private void boardMatchValidate(Todo todo, Board board) {
    if (todo.isNotBoardMatch(board)) {
      throw new EntityNotMatchException("해당 보드에 속한 컬럼이 아닙니다.");
    }
  }

  private void todoMatchValidate(Card card, Todo todo) {
    if (card.isNotTodoMatch(todo)) {
      throw new EntityNotMatchException("해당 컬럼에 속한 카드가 아닙니다.");
    }
  }

  private void inviteCheckValidate(Long boardId, Member member) {
    if (!inviteRepository.existsByBoard_BoardIdAndMember_MemberId(boardId, member.getMemberId())) {
      throw new EntityExistsException("권한이 없는 유저입니다.");
    }
  }
}
