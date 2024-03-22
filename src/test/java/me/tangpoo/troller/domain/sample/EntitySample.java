//package me.tangpoo.troller.domain.sample;
//
//import me.tangpoo.troller.domain.board.entity.Board;
//import me.tangpoo.troller.domain.card.entity.Card;
//import me.tangpoo.troller.domain.todo.entity.Todo;
//
//public interface EntitySample {
//
//  String TEST_BOARD_TITLE = "TEST";
//  String TEST_BOARD_DESCRIPTION = "TEST";
//  String TEST_BOARD_COLOR = "BLACK";
//
//  Board TEST_BOARD = new Board(
//      TEST_BOARD_TITLE,
//      TEST_BOARD_DESCRIPTION,
//      TEST_BOARD_COLOR
//  );
//
//  String TEST_TODO_NAME = "TODO";
//  String TEST_TODO_ORDER = "1";
//
//  Todo TEST_TODO = new Todo(
//      TEST_BOARD,
//      TEST_TODO_NAME,
//      TEST_TODO_ORDER
//  );
//
//  String TEST_CARD_NAME = "Card";
//  String TEST_CARD_DESCRIPTION = "할 일 목록";
//  String TEST_CARD_COLOR = "BLACK";
//  int TEST_CARD_ORDER_NUM = 1;
//
//  Card TEST_CARD = Card.builder()
//      .name(TEST_CARD_NAME)
//      .description(TEST_CARD_DESCRIPTION)
//      .color(TEST_CARD_COLOR)
//      .cardOrder(TEST_CARD_ORDER_NUM)
//      .todo(TEST_TODO)
//      .build();
//
//}
