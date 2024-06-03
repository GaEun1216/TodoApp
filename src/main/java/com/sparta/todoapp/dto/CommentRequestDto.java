package com.sparta.todoapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentRequestDto {
    private Long Id;
    private String contents;
    private String userName;
    private LocalDateTime createdAt;
    private Long TodoId;

}
