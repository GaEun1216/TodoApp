package com.sparta.todoapp.controller;

import com.sparta.todoapp.CommonResponse;
import com.sparta.todoapp.dto.CommentRequestDto;
import com.sparta.todoapp.dto.CommentResponseDto;
import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.security.UserDetailsImpl;
import com.sparta.todoapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;


    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    // 2. 댓글 등록
    @PostMapping("/{Todoid}")
    public ResponseEntity<CommonResponse<CommentResponseDto>> createComment(@PathVariable Long Todoid, @RequestBody CommentRequestDto requestDto,
                                                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        CommentResponseDto response = commentService.createComment(Todoid, requestDto,userDetails.getUser());

        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("댓글 등록이 완료되었습니다.")
                .data(response).build());
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<CommentResponseDto>>> getComments(@AuthenticationPrincipal UserDetailsImpl userDetails){
        List<Comment> comments = commentService.getComments(userDetails.getUser());
        List<CommentResponseDto> responses = comments.stream().map(CommentResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(CommonResponse.<List<CommentResponseDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("전체 댓글 조회가 완료되었습니다.")
                .data(responses).build());
    }



    // 3. 댓글 수정
    @PutMapping("/{Todoid}/{Commentid}")
    public ResponseEntity<CommonResponse<CommentResponseDto>> putComment(@PathVariable Long Todoid,@PathVariable Long Commentid, @RequestBody CommentRequestDto dto){
        CommentResponseDto response = commentService.updateComment(Todoid,Commentid, dto);
        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("댓글 수정이 완료되었습니다.")
                .data(response).build());
    }

    // 4. 댓글 삭제
    @DeleteMapping("/{Todoid}/{Commentid}")
    public ResponseEntity<CommonResponse> deleteComment(@PathVariable Long Todoid,@PathVariable Long Commentid, @RequestBody CommentRequestDto dto){
        commentService.deleteComment(Todoid, Commentid, dto);
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("댓글 삭제가 완료되었습니다.")
                .build());
    }
}
