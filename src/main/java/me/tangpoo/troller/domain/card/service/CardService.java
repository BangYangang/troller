package me.tangpoo.troller.domain.card.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.board.entity.Board;
import me.tangpoo.troller.domain.board.repository.BoardRepository;
import me.tangpoo.troller.domain.card.dto.CreateCardForm;
import me.tangpoo.troller.domain.card.dto.ResponseCardForm;
import me.tangpoo.troller.domain.card.dto.UpdateCardForm;
import me.tangpoo.troller.domain.card.entity.Card;
import me.tangpoo.troller.domain.card.repository.CardRepository;
import me.tangpoo.troller.domain.todo.entity.Todo;
import me.tangpoo.troller.domain.todo.repository.TodoRepository;
import me.tangpoo.troller.global.exception.custom.EntityNotMatchException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CardService {

  private final CardRepository cardRepository;
  private final BoardRepository boardRepository;
  private final TodoRepository todoRepository;

  @Transactional
  public String create(Long boardId, Long todoId, CreateCardForm dto) {
    Board board = findBoardBy(boardId);
    Todo todo = findTodoBy(todoId);

    boardMatchValidate(todo, board);

    int orderNum = cardRepository.countCardsByTodo(todo) + 1;

    cardRepository.save(
        Card.builder()
            .todo(todo)
            .cardOrder(orderNum)
            .color("black")
            .name(dto.getName())
            .description(dto.getDescription())
            .deadline(dto.getDeadline())
            .build()
    );

    return "카드 작성을 완료했습니다.";
  }

  public ResponseCardForm get(Long boardId, Long todoId, Long cardId) {
    Board board = findBoardBy(boardId);
    Todo todo = findTodoBy(todoId);
    Card card = findCardBy(cardId);

    boardMatchValidate(todo, board);
    todoMatchValidate(card, todo);

    return card.createCardResponseDto();
  }

  @Transactional
  public String delete(Long boardId, Long todoId, Long cardId) {
    Board board = findBoardBy(boardId);
    Todo todo = findTodoBy(todoId);
    Card card = findCardBy(cardId);

    boardMatchValidate(todo, board);
    todoMatchValidate(card, todo);

    cardRepository.delete(card);
    return "삭제가 완료되었습니다!";
  }


  @Transactional
  public ResponseCardForm update(Long boardId, Long cardId, Long todoId, UpdateCardForm dto) {
    Board board = findBoardBy(boardId);
    Todo todo = findTodoBy(todoId);
    Card card = findCardBy(cardId);

    boardMatchValidate(todo, board);
    todoMatchValidate(card, todo);

    card.update(dto);

    return card.createCardResponseDto();
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


}
