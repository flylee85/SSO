package com.sso.server.controller;

import com.awesome.util.http.ApiResponse;
import com.awesome.util.util.MapUtil;
import com.sso.client.util.SSOUserUtils;
import com.sso.server.service.UserService;
import com.sso.server.vo.request.LoginReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mjd on 2020/5/14 13:18
 */
@Controller
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    @ResponseBody
    public ApiResponse login(@RequestBody LoginReq loginReq) {
        String name = loginReq.getName();
        String pwd = loginReq.getPwd();
        String token = userService.login(name, pwd);
        return ApiResponse.ok(MapUtil.newMap().put("access-token", token));
    }

    @GetMapping("info")
    public ApiResponse info() {
        return ApiResponse.ok(MapUtil.newMap().put("name", SSOUserUtils.getUserName()));
    }

}
