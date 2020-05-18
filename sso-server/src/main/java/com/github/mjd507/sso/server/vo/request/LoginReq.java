package com.github.mjd507.sso.server.vo.request;

import lombok.Data;

/**
 * Created by mjd on 2020/4/17 17:17
 */
@Data
public class LoginReq {
    private String name;
    private String pwd;
    private String callbackUrl;
}
