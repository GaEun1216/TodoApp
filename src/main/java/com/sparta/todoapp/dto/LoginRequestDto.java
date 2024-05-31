package com.sparta.todoapp.dto;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    private String username;
    private String password;
}
