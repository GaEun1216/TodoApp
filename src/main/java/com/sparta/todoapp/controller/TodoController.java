package com.sparta.todoapp.controller;

import com.sparta.todoapp.CommonResponse;
import com.sparta.todoapp.dto.TodoRequestDto;
import com.sparta.todoapp.dto.TodoResponseDto;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    public final TodoService todoService;

    public TodoController(TodoService todoService){
        this.todoService = todoService;
    }

    // 일정 작성
    @PostMapping
    public ResponseEntity<CommonResponse<TodoResponseDto>> postTodo(@RequestBody TodoRequestDto dto){
        // TODO 일정 작성 기능
        Todo todo = todoService.createTodo(dto);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                .msg("생성이 완료되었습니다.")
                .data(response).build());
    }

    // 일정 선택 조회
    @GetMapping("/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDto>> getTodo(@PathVariable Long todoId){
        Todo todo = todoService.getTodo(todoId);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("일정 조회가 완료되었습니다.")
                .data(response).build());
    }

    // 일정 전체 조회
    @GetMapping
    public ResponseEntity<CommonResponse<List<TodoResponseDto>>> getTodos(){
        List<Todo> todos = todoService.getTodos();
        List<TodoResponseDto> responses = todos.stream().map(TodoResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(CommonResponse.<List<TodoResponseDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("일정 조회가 완료되었습니다.")
                .data(responses).build());
    }

    // 일정 변경
    @PutMapping("/{todoId}")
    public ResponseEntity<CommonResponse<TodoResponseDto>> putTodo(@PathVariable Long todoId,@RequestBody TodoRequestDto dto){
        Todo todo = todoService.updateTodo(todoId, dto);
        TodoResponseDto response = new TodoResponseDto(todo);
        return ResponseEntity.ok().body(CommonResponse.<TodoResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("일정 변경이 완료되었습니다.")
                .data(response).build());
    }

    // 일정 삭제
    @DeleteMapping("/{todoId}")
    public ResponseEntity<CommonResponse> deleteTodo(@PathVariable Long todoId,@RequestBody TodoRequestDto dto){
        todoService.deleteTodo(todoId,dto.getPassword());
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("일정 삭제가 완료되었습니다.")
                .build());
    }
}
