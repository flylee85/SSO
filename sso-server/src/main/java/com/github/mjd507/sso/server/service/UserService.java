package com.github.mjd507.sso.server.service;


import com.github.mjd507.sso.server.mapper.UserMapper;
import com.github.mjd507.sso.server.util.SSOConfig;
import com.github.mjd507.sso.server.vo.request.LoginReq;
import com.github.mjd507.util.spring.RedisOperation;
import com.github.mjd507.util.util.Md5Util;
import com.github.mjd507.sso.client.entity.SSOUser;
import com.github.mjd507.sso.server.vo.response.LoginResp;
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

    @Autowired
    private SSOService ssoService;

    /**
     * @return LoginResp 包含两个字段
     * 1. TGT: 32 为字符串
     * 2. ticket: ST-开头8位数字
     */
    public LoginResp login(LoginReq loginReq) {
        SSOUser user = userMapper.selectUserByNameAndPwd(loginReq.getName(), Md5Util.digist(loginReq.getPwd()));
        if (user == null) {
            return null;
        }
        String TGT = UUID.randomUUID().toString().replace("-", "");
        redisOperation.set(TGT, user.getId() + "", SSOConfig.EXPIRE_TIME_TGT);
        String ST = ssoService.generateST(TGT);
        LoginResp loginResp = new LoginResp();
        loginResp.setTGT(TGT);
        loginResp.setST(ST);
        return loginResp;
    }

    public SSOUser getUserById(Integer uId) {
        return userMapper.selectUserById(uId);
    }

    public SSOUser getUserByST(String ST) {
        String TGT = redisOperation.get(ST);
        String userId = redisOperation.get(TGT);
        return getUserById(Integer.parseInt(userId));
    }
}
