package com.sso.server.controller;


import com.awesome.util.http.ApiCode;
import com.awesome.util.http.ApiResponse;
import com.awesome.util.util.MapUtil;
import com.sso.client.entity.SSOUser;
import com.sso.server.service.SSOService;
import com.sso.server.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mjd on 2020/4/12 22:49
 */
@Controller
@RequestMapping("sso")
@Slf4j
public class SSOController {

    @Autowired
    private SSOService ssoService;

    @Autowired
    private UserService userService;

    @GetMapping(value = {"", "/", "/index"})
    public String index() {
        // SSO 主页: 判断是否需要登录。
        // 需要登录，进入login 页面，
        // 否则直接生成一个 ticket 返回前端。
        return "views/index";
    }

    @GetMapping(value = "login")
    public String login() {
        return "views/login";
    }

    @GetMapping(value = "valid_TGC")
    @ResponseBody
    public ApiResponse validTGC(@RequestParam String TGC) {
        boolean valid = ssoService.validTGC(TGC);
        if (valid) {
            return ApiResponse.ok();
        }
        return ApiResponse.error(ApiCode.UNAUTHORISED);
    }

    @GetMapping(value = "generate_ST")
    @ResponseBody
    public ApiResponse genTicketByTGC(@RequestParam String TGC) {
        String ticket = ssoService.generateST(TGC);
        return ApiResponse.ok(MapUtil.newMap().put("ST", ticket));
    }

    @GetMapping(value = "valid_ST")
    @ResponseBody
    public SSOUser validST(@RequestParam String ST) {
        boolean valid = ssoService.validST(ST);
        if (valid) {
            return userService.getUserByST(ST);
        }
        return null;
    }


    @GetMapping(value = {"/callback"})
    public String callback() {
        return "views/callback";
    }


}
