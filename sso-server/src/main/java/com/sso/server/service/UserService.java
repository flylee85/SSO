package com.sso.server.service;


import com.awesome.util.spring.RedisOperation;
import com.awesome.util.util.Md5Util;
import com.sso.client.entity.SSOUser;
import com.sso.server.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by mjd on 2020/4/16 16:51
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisOperation redisOperation;

    public String login(String name, String pwd) {
        SSOUser user = userMapper.selectUserByNameAndPwd(name, Md5Util.digist(pwd));
        if (user == null) {
            return null;
        }
        String accessToken = UUID.randomUUID().toString().replace("-", "");
        redisOperation.set(accessToken, user.getId() + "", 30 * 24 * 60 * 60);
        return accessToken;
    }

    public static void main(String[] args) {
        System.out.println(Md5Util.digist("123456"));
    }

    public SSOUser getUserById(Integer uId) {
        return userMapper.selectUserById(uId);
    }

}
