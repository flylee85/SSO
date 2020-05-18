package com.github.mjd507.sso.server.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mjd on 2020/5/15 17:30
 */
public class LoginResp {
    private String TGT; // 相当于用户 sessionId，这里存储在 redis，作为key, value 为 userId
    private String ST; // 票据，存储在 redis，作为 key, value 为 TGT/TGC

    @JsonProperty("TGT")
    public String getTGT() {
        return TGT;
    }

    public void setTGT(String TGT) {
        this.TGT = TGT;
    }

    @JsonProperty("ST")
    public String getST() {
        return ST;
    }

    public void setST(String ST) {
        this.ST = ST;
    }
}
