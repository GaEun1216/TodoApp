package com.sparta.todoapp.repository;

import com.sparta.todoapp.entity.Comment;
import com.sparta.todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByTodo_TodoId(Long todoId);
}
