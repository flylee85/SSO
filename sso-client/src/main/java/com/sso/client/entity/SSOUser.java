package com.sso.client.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SSOUser {

    private Integer id;

    private String name;

    private String password;

    private Date createAt;

    private Date updateAt;
}