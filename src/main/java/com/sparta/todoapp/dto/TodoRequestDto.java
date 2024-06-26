package com.sparta.todoapp.dto;

import com.sparta.todoapp.entity.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequestDto {

    private String title;

    private String content;


    public Todo toEntity(){
        return Todo.builder()
                .title(title)
                .content(content)
                .build();

    }
}
