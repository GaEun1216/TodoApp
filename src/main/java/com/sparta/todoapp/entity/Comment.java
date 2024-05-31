package com.sparta.todoapp.entity;

import com.sparta.todoapp.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String contents;

    private Long userId;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="todo_id",nullable = false)
    private Todo todo;

    public Comment(CommentRequestDto request){
        this.contents = request.getContents();
        this.userId = request.getUserId();
    }

    public Comment(String contents,Long userId) {
        this.contents = contents;
        this.userId = userId;

    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}
