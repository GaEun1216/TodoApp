package com.sparta.todoapp.controller;

import com.sparta.todoapp.repository.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoResponseDto {

    private Long id;

    private String title;

    private String content;

    private String userName;

    private LocalDateTime createAt;

    public TodoResponseDto(Todo todo){
        this.id = todo.getTodoId();
        this.title = todo.getTitle();
        this.content = todo.getContent();
        this.userName = todo.getUserName();
        this.createAt = todo.getCreatedAt();
    }
}
