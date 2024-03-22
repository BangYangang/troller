//package me.tangpoo.troller.domain.card.service;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//import java.util.Optional;
//import me.tangpoo.troller.domain.board.repository.BoardRepository;
//import me.tangpoo.troller.domain.card.dto.CreateCardForm;
//import me.tangpoo.troller.domain.card.repository.CardRepository;
//import me.tangpoo.troller.domain.sample.EntitySample;
//import me.tangpoo.troller.domain.todo.entity.Todo;
//import me.tangpoo.troller.domain.todo.repository.TodoRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class CardServiceTest implements EntitySample {
//
//  @Mock
//  TodoRepository todoRepository;
//  @Mock
//  BoardRepository boardRepository;
//  @Mock
//  CardRepository cardRepository;
//
//  @InjectMocks
//  CardService cardService;
//
//  @Test
//  public void 카드_생성(){
//    Long boardId = 1L;
//    Long todoId = 1L;
//    CreateCardForm createCardForm = CreateCardForm.builder()
//        .name("테스트")
//        .description("테스트")
//        .build();
//
//    given(boardRepository.findById(any())).willReturn(Optional.of(TEST_BOARD));
//    given(todoRepository.findById(any())).willReturn(Optional.of(TEST_TODO));
//    given(cardRepository.countCardsByTodo(any(Todo.class))).willReturn(1);
//
//    assertThat(cardService.create(boardId, todoId, createCardForm))
//        .isEqualTo("카드 작성을 완료했습니다.");
//  }
//
//}