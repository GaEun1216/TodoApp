package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.SignupRequestDto;
import com.sparta.todoapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j(topic = "UserController")
@Controller
@RequiredArgsConstructor
//@Tag(name = "UserController", description = "사용자 관련 api")
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

//    @Operation(summary = "회원가입", description = "별명, 사용자이름, 비밀번호는 필수입력이며, Admin계정은 별도 과정 필요함.")
    @PostMapping("/user/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(!fieldErrors.isEmpty()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>("회원가입 정보가 잘못되었습니다.", HttpStatus.BAD_REQUEST);
        }

        userService.signup(requestDto);

        return new ResponseEntity<>("회원가입 성공!!\nHttpStatus : "+ HttpStatus.OK, HttpStatus.OK);
    }

}