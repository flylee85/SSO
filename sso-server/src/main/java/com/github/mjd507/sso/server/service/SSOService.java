package com.github.mjd507.sso.server.service;

import com.github.mjd507.sso.server.util.SSOConfig;
import com.github.mjd507.util.spring.RedisOperation;
import com.google.common.base.Strings;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2020/5/15 17:56
 */
@Service
public class SSOService {

    @Autowired
    private RedisOperation redisOperation;

    public String generateST(String TGT) {
        String ST = "ST-" + RandomUtils.nextInt(1000, 9999) + "-" + RandomUtils.nextInt(1000, 9999);
        redisOperation.set(ST, TGT, SSOConfig.EXPIRE_TIME_TICKET);
        return ST;
    }

    public boolean validST(String ST) {
        String TGT = redisOperation.get(ST);
        if (Strings.isNullOrEmpty(TGT)) return false;
        String userId = redisOperation.get(TGT);
        if (Strings.isNullOrEmpty(userId)) return false;
        return true;
    }

    public boolean validTGC(String TGC) {
        String userId = redisOperation.get(TGC);
        if (Strings.isNullOrEmpty(userId)) return false;
        return true;
    }

}
