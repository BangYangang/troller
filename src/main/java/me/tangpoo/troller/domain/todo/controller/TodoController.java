package me.tangpoo.troller.domain.todo.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.tangpoo.troller.domain.todo.dto.ResponseForm;
import me.tangpoo.troller.domain.todo.dto.ToDoResponse;
import me.tangpoo.troller.domain.todo.dto.TodoMoveRequestDto;
import me.tangpoo.troller.domain.todo.dto.TodoRequestDto;
import me.tangpoo.troller.domain.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boards")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/{board_id}/todos")
    public ResponseEntity<String> createTodo(@PathVariable Long board_id, @RequestBody TodoRequestDto requestDto) {
        todoService.createTodo(board_id, requestDto);
        return new ResponseEntity<>("todo 생성 성공", HttpStatus.CREATED);
    }

    @GetMapping("/{board_id}/todos")
    public ResponseEntity<List<ToDoResponse>> getTodos(@PathVariable Long board_id) {
        List<ToDoResponse> todoList = todoService.getTodos(board_id);
        return new ResponseEntity<>(todoList, HttpStatus.OK);
    }


    @PutMapping("/{board_id}/todo/{todo_id}")
    public ResponseEntity<String> updateTodo(@PathVariable Long board_id,@PathVariable Long todo_id,@RequestBody TodoRequestDto todoRequestDto ) {
        todoService.updateTodo(board_id,todo_id,todoRequestDto);
        return new ResponseEntity<>("Todo 수정 성공", HttpStatus.OK);
    }

    @DeleteMapping("/{board_id}/todo/{todo_id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long board_id,@PathVariable Long todo_id) {
        todoService.deleteTodo(board_id,todo_id);
        return new ResponseEntity<>("Todo 삭제 성공", HttpStatus.NO_CONTENT);
    }


    @PutMapping("/{board_id}/move")
    public ResponseEntity<String> moveTodo(@PathVariable Long board_id, @RequestBody TodoMoveRequestDto todoRequestDto ) {
        todoService.moveTodo(board_id,todoRequestDto);
        return new ResponseEntity<>("Todo 이동 성공", HttpStatus.OK);
    }

}
