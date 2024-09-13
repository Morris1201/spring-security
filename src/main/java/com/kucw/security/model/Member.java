package com.kucw.security.model;

import lombok.Data;

import java.util.List;

@Data
public class Member {

    private Integer memberId;
    private String email;
    private String password;
    private String name;
    private Integer age;
    private List<Role> roles;
}
