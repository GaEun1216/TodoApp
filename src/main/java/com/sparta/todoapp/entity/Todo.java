package com.sparta.todoapp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "todo")
@NoArgsConstructor
public class Todo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name="todo_id",nullable = false)
    private long todoId;

    private String title;

    private String content;

    private String userName;

    private String password;

    @OneToMany(mappedBy = "todo",cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Todo(String title, String content, String userName, String password) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.password = password;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
