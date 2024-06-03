package com.sparta.todoapp.entity;

import com.sparta.todoapp.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor
public class Comment {

    /*
    | 댓글 필드 | 데이터 유형 |
    | --- | --- |
    | 아이디 (고유번호) | bigint |
    | 댓글 내용 | varchar |
    | 사용자 아이디 | varchar |
    | 일정 아이디 | bigint |
    | 작성일자 | timestamp |
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long CommentId;

    private String contents;

    private String userId;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name="todo_id",nullable = false)
    private Todo todo;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    public Comment(CommentRequestDto request){
        this.contents = request.getContents();
        this.userId = request.getUserId();
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public void update(CommentRequestDto dto) {
        this.contents = dto.getContents();
    }
}
