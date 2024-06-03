package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Comment;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ReadTodoResponseDto {
    private long todoId;

    private String title;

    private String content;

    private String userName;

    private String password;

    private List<Comment> comments = new ArrayList<>();
}
