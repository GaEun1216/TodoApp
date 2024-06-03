package com.sparta.todoapp.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String message;
    private String token;

    public LoginResponseDto(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
