package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TodoResponseDto {

    private Long id;

    private String title;

    private String content;

    private String userName;

    private LocalDateTime createAt;

    private List<Comment> comments = new ArrayList<>();

    public TodoResponseDto(Todo todo){
        this.id = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.userName = todo.getUserName();
        this.createAt = todo.getCreatedAt();
        this.comments = todo.getComments();
    }
}
