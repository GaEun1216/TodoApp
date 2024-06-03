package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {

    private Long id;

    private String contents;

    private String userId;

    private Long TodoId;

    private LocalDateTime createAt;


    public CommentResponseDto(Comment comment) {
        this.id = comment.getCommentId();
        this.contents = comment.getContents();
        this.userId = comment.getUser().getUsername();
        this.TodoId = comment.getTodo().getTodoId();
        this.createAt = comment.getCreatedAt();
    }
}
