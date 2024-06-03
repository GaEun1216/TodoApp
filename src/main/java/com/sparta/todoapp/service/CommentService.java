package com.sparta.todoapp.service;

import com.sparta.todoapp.dto.CommentRequestDto;
import com.sparta.todoapp.dto.CommentResponseDto;
import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.User;
import com.sparta.todoapp.exception.DataNotFoundException;
import com.sparta.todoapp.repository.CommentRepository;
import com.sparta.todoapp.entity.Todo;
import com.sparta.todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    public CommentResponseDto createComment(Long TodoId, CommentRequestDto requestDto, User user) {
        Todo todo = todoRepository.findById(TodoId).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 게시물 입니다."));
        Comment comment = new Comment(requestDto,user,todo);
        Comment savecomment = commentRepository.save(comment);
        CommentResponseDto response = new CommentResponseDto(savecomment);

        return  response;

    }

    public List<Comment> getComments() {
        return commentRepository.findAll(Sort.by("createdAt").descending());
    }

    @Transactional
    public CommentResponseDto updateComment(Long TodoId, Long CommentId, CommentRequestDto dto, User user) {
        Comment updatecomment = filter(TodoId,CommentId,user);
        updatecomment.update(dto, user);
        CommentResponseDto response = new CommentResponseDto(updatecomment);
        return  response;
    }

    public void deleteComment(Long TodoId, Long CommentId, User user) {
        Comment deletecomment = filter(TodoId,CommentId, user);
        commentRepository.delete(deletecomment);
    }

    // 예외 처리 필터링 함수
    private Comment filter(Long TodoId, Long CommentId, User user){
        Comment resultcomment = findCommentById(CommentId);
        if(resultcomment.getTodo().getTodoId() != TodoId){
            throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
        }
        if(! user.getUsername().equals(resultcomment.getUser().getUsername())){
            throw new IllegalArgumentException("사용자가 일치하지 않습니다.");
        }
        return resultcomment;
    }

    private Comment findCommentById(Long CommentId) {
        return commentRepository.findById(CommentId).orElseThrow(
                () -> new DataNotFoundException("존재하지 않는 댓글입니다."));
    }


}
