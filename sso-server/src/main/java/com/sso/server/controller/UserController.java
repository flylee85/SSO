package com.sso.server.controller;

import com.github.mjd507.util.http.ApiResponse;
import com.github.mjd507.util.util.MapUtil;
import com.sso.client.util.SSOUserUtils;
import com.sso.server.service.UserService;
import com.sso.server.vo.request.LoginReq;
import com.sso.server.vo.response.LoginResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("info")
    public ApiResponse info() {
        return ApiResponse.ok(MapUtil.newMap().put("name", SSOUserUtils.getUserName()));
    }

}
