//package com.sparta.todoapp.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//public class User extends Timestamped{
//    /*
//    아이디 bigint
//    별명 varchar
//    사용자이름 (username) varchar
//    비밀번호 (password) varchar
//    권한 (일반, 어드민) varchar
//    생성일 timestamp
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "nickname", unique = true, nullable = false, length = 255)
//    private String nickname;
//
//    @Column(name = "username", nullable = false, length = 255)
//    private String username;
//
//    @Column(name = "password", nullable = false, length = 255)
//    private String password;
//
//    @Column(nullable = false)
//    @Enumerated(value = EnumType.STRING)
//    private UserRoleEnum role; // 관리자 유무
//
//    // 일대다 관계
//    @OneToMany(mappedBy = "user")
//    private List<Comment> commentList = new ArrayList<>();
//
//    public User(String nickname, String username, String password, UserRoleEnum role) {
//        this.nickname = nickname;
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }
//}
