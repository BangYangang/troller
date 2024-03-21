package me.tangpoo.troller.domain.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import me.tangpoo.troller.domain.todo.dto.TodoMoveRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodoServiceTest {
    @Autowired
    TodoService todoService;

    @Test
    @DisplayName("게시글 이동 동시성 테스트")
    public  void  getAllSchedulesTest(){
        // given
        List<TodoMoveRequestDto> dtoList = new ArrayList<>();

        TodoMoveRequestDto list1 = new TodoMoveRequestDto(4L,2L,null);
        dtoList.add(list1);

        TodoMoveRequestDto list2 = new TodoMoveRequestDto(5L,3L,null);
        dtoList.add(list2);

        TodoMoveRequestDto list3 = new TodoMoveRequestDto(6L,1L,null);
        dtoList.add(list3);

        TodoMoveRequestDto todoRequsetDto = new TodoMoveRequestDto(null, null, dtoList);

        // when
        IntStream.range(0, 1000).parallel().forEach(i -> {

            try {
                todoService.moveTodo(2L,todoRequsetDto);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });


    }

}
