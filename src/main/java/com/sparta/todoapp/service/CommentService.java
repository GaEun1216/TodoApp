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

    public CommentResponseDto createComment(Long Todoid, CommentRequestDto requestDto, User user) {
        Todo todo = todoRepository.findById(Todoid).orElseThrow(
                ()->new IllegalArgumentException("존재하지 않는 게시물 입니다."));
        Comment comment = new Comment(requestDto,user,todo);
        Comment savecomment = commentRepository.save(comment);
        CommentResponseDto response = new CommentResponseDto(savecomment);

        return  response;

    }

    public List<Comment> getComments(User user) {
        return commentRepository.findAll(Sort.by("createdAt").descending());
    }

    @Transactional
    public CommentResponseDto updateComment(Long Todoid, Long Commentid, CommentRequestDto dto) {
        Comment updatecomment = filter(Todoid,Commentid,dto);
        updatecomment.update(dto);
        CommentResponseDto response = new CommentResponseDto(updatecomment);
        return  response;
    }

    public void deleteComment(Long Todoid, Long Commentid, CommentRequestDto dto) {
        Comment deletecomment = filter(Todoid,Commentid,dto);
        commentRepository.delete(deletecomment);
    }

    // 예외 처리 필터링 함수
    private Comment filter(Long todoid, Long commentid, CommentRequestDto dto){
        Comment resultcomment = findCommentById(commentid);
        if(resultcomment.getTodo().getTodoId() != todoid){
            throw new IllegalArgumentException("존재하지 않는 댓글입니다.");
        }
        if(!dto.getUserName().equals(resultcomment.getUser().getUsername())){
            throw new IllegalArgumentException("사용자가 일치하지 않습니다.");
        }
        return resultcomment;
    }

    private Comment findCommentById(Long commentid) {
        return commentRepository.findById(commentid).orElseThrow(
                () -> new DataNotFoundException("존재하지 않는 댓글입니다."));
    }


}
