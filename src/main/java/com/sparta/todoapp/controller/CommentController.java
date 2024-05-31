package com.sparta.todoapp.controller;

import com.sparta.todoapp.dto.CommentRequestDto;
import com.sparta.todoapp.dto.CommentResponseDto;
import com.sparta.todoapp.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    @PostMapping("/{id}")
    public CommentResponseDto createComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(id, requestDto);
    }

}
