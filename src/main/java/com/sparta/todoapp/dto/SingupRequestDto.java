package com.sparta.todoapp.dto;


import jakarta.validation.constraints.Pattern;

public class SingupRequestDto {
    /*
    - `username`은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
    - `password`는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성되어야 한다.
     */

    private String nickname;

    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private String password;

    private boolean admin = false;
    private String adminToken = ""; // 공백
}
