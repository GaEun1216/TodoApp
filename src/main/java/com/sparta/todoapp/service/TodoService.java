package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.TodoRequestDto;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // 할일 생성
    public Todo createTodo(TodoRequestDto dto){
        var newTodo = dto.toEntity();
        return todoRepository.save(newTodo);
    }

    // 할일 단건 조회
    public Todo getTodo(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(IllegalArgumentException::new);
    }

    // 할일 전체 조회
    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by("createdAt").descending());
    }

    public Todo updateTodo(Long todoId, TodoRequestDto dto) {
        Todo todo = checkAndGetTodo(todoId,dto.getPassword());
        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());
        todo.setUserName(dto.getUserName());
        return todo;
    }

    public void deleteTodo(Long todoId, String password) {
        Todo todo = checkAndGetTodo(todoId,password);
        todoRepository.delete(todo);
    }

    private Todo checkAndGetTodo(Long todoId, String password){
        Todo todo = getTodo(todoId);

        // 비밀번호 체크
        if(todo.getPassword() != null
                && !todo.getPassword().equals(password)){
            throw new IllegalArgumentException();
        }
        return todo;
    }
}
