package com.ll.exam.mybatis.app.member.dto;

import lombok.Data;

@Data
public class Member {

    private long id;
    private String username;
    private String password;
    private String name;
    private String emil;

    public boolean matchPassword(String password) {
        return this.password.substring("{noop}".length()).equals(password);
    }
}
