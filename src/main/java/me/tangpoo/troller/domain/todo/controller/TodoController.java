package me.tangpoo.troller.domain.todo.controller;

import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.todo.dto.ToDoResponse;
import me.tangpoo.troller.domain.todo.dto.TodoMoveRequestDto;
import me.tangpoo.troller.domain.todo.dto.TodoRequestDto;
import me.tangpoo.troller.domain.todo.service.TodoService;
import me.tangpoo.troller.global.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards/{board_id}/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<String> createTodo(@PathVariable Long board_id,
        @RequestBody TodoRequestDto requestDto) {
        todoService.createTodo(board_id, requestDto);
        return new ResponseEntity<>("todo 생성 성공", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ToDoResponse>> getTodos(@PathVariable Long board_id) {
        List<ToDoResponse> todoList = todoService.getTodos(board_id);
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }


    @PutMapping("/{todo_id}")
    public ResponseEntity<String> updateTodo(@PathVariable Long board_id,
        @PathVariable Long todo_id, @RequestBody TodoRequestDto todoRequestDto,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        todoService.updateTodo(board_id, todo_id, todoRequestDto,userDetails.getMember().getMemberId());
        return new ResponseEntity<>("Todo 수정 성공", HttpStatus.OK);
    }

    @DeleteMapping("/{todo_id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long board_id,
        @PathVariable Long todo_id,@AuthenticationPrincipal UserDetailsImpl userDetails) {
        todoService.deleteTodo(board_id, todo_id,userDetails.getMember().getMemberId());
        return new ResponseEntity<>("Todo 삭제 성공", HttpStatus.NO_CONTENT);
    }


    @PutMapping("/move")
    public ResponseEntity<String> moveTodo(@PathVariable Long board_id,
        @RequestBody TodoMoveRequestDto todoRequestDto) {
        todoService.moveTodo(board_id, todoRequestDto);
        return new ResponseEntity<>("Todo 이동 성공", HttpStatus.OK);
    }

}
