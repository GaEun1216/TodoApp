package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Todo;
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
        this.createAt = todo.getCreatedAt();
        this.userName = todo.getUser().getUsername();
    }
}
