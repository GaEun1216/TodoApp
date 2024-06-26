package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.TodoRequestDto;
import com.sparta.todoapp.dto.TodoResDto;
import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.repository.CommentRepository;
import com.sparta.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    // 할일 생성
    public Todo createTodo(TodoRequestDto dto, User user){
        Todo newTodo = dto.toEntity();
        newTodo.setUser(user);
        return todoRepository.save(newTodo);
    }

    // 할일 단건 조회
    public TodoResDto getTodo(Long TodoId) {
        List<Comment> comments = commentRepository.findByTodo_TodoId(TodoId);
        Todo todo = todoRepository.findById(TodoId).orElseThrow(IllegalArgumentException::new);

        return new TodoResDto(todo,comments);
    }

    // 할일 전체 조회
    public List<Todo> getTodos() {
        return todoRepository.findAll(Sort.by("createdAt").descending());
    }

    public Todo updateTodo(Long TodoId, TodoRequestDto dto, User user) {
        Todo todo = checkAndGetTodo(TodoId,user.getPassword());
        todo.setTitle(dto.getTitle());
        todo.setContent(dto.getContent());
        todo.setUser(user);

        return todo;
    }

    public void deleteTodo(Long TodoId, String password) {
        Todo todo = checkAndGetTodo(TodoId,password);
        todoRepository.delete(todo);
    }

    private Todo checkAndGetTodo(Long TodoId, String password){
        Todo todo = todoRepository.findById(TodoId).orElseThrow();

        // 비밀번호 체크
        if(!todo.getUser().getPassword().equals(password)){
            throw new IllegalArgumentException();
        }
        return todo;
    }
}
