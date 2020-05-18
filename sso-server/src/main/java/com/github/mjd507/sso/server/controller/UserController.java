package com.github.mjd507.sso.server.controller;

import com.github.mjd507.sso.server.service.UserService;
import com.github.mjd507.sso.server.vo.request.LoginReq;
import com.github.mjd507.sso.server.vo.response.LoginResp;
import com.github.mjd507.util.http.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2020/5/14 13:18
 */
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ApiResponse login(@RequestBody LoginReq loginReq) {
        LoginResp loginResp = userService.login(loginReq);
        return ApiResponse.ok(loginResp);
    }
}
