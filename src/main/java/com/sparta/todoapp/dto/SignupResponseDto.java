package com.sparta.todoapp.dto;

import lombok.Getter;

@Getter
public class SignupResponseDto {
    private String message;

    public SignupResponseDto(String message) {
        this.message = message;
    }
}
