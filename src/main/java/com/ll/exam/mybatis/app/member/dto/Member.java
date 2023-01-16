package com.ll.exam.mybatis.app.member.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;

@Data
@Builder
public class Member {

    private long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String roles;

    public boolean matchPassword(String password) {
        return this.password.substring("{noop}".length()).equals(password);
    }

    public boolean hasRole(String role) {

        return Arrays
                .stream(roles.split(","))
                .anyMatch(role_ -> role_.equals(role));
    }
}
