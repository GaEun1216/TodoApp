package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.Todo;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TodoResDto {
    private TodoResponseDto todores;
    private List<CommentResponseDto> commentres;

    public TodoResDto(Todo todo, List<Comment> comments) {
        this.todores = new TodoResponseDto(todo);
        this.commentres = comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
