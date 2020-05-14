package com.sso.server.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mjd on 2020/4/12 22:49
 */
@Controller
@RequestMapping("sso")
@Slf4j
public class SSOController {

    @GetMapping(value = {"", "/", "/index"})
    public String index() {
        return "views/login";
    }

    @GetMapping(value = {"/callback"})
    public String callback() {
        return "views/callback";
    }


}
