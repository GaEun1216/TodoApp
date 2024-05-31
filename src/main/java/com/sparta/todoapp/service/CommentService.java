package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.CommentRequestDto;
import com.sparta.todoapp.dto.CommentResponseDto;
import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.repository.CommentRepository;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentResponseDto createComment(Long id, CommentRequestDto requestDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 게시물 입니다."));
        Comment comment = new Comment(requestDto);
        comment.setTodo(todo);
        comment.setCreatedAt();
        Comment savecomment = commentRepository.save(comment);
        CommentResponseDto response = new CommentResponseDto(savecomment);
        return  response;

    }
}
