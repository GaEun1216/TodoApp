package com.sparta.todoapp.entity;

import com.sparta.todoapp.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")
@NoArgsConstructor // 생성자 만들어주는 어노테이션
public class Comment extends BaseEntity {

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

    @ManyToOne
    @JoinColumn(name="todo_id",nullable = false)
    private Todo todo;


    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    public Comment(CommentRequestDto request,User user,Todo todo){
        this.contents = request.getContents();
        this.user = user;
        this.todo = todo;
    }

    public void update(CommentRequestDto dto, User user) {
        this.contents = dto.getContents();
        this.user = user;
    }
}
