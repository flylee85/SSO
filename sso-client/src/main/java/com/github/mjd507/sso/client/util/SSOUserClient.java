package com.github.mjd507.sso.client.util;

import com.github.mjd507.sso.client.entity.SSOUser;
import com.github.mjd507.util.http.HttpMethod;
import com.github.mjd507.util.http.HttpRequest;
import com.github.mjd507.util.http.HttpUtils;
import com.github.mjd507.util.util.MapUtil;

/**
 * Created by mjd on 2020/5/18 21:51
 */
public class SSOUserClient {

    private final String ssoServerUrl;

    public SSOUserClient(String ssoServerUrl) {
        this.ssoServerUrl = ssoServerUrl;
    }

    public SSOUser getUser(String ticket) {
        HttpRequest request = new HttpRequest();
        request.setUrl(ssoServerUrl + "/sso/get_user_by_ST");
        request.setMethod(HttpMethod.GET);
        request.setParams(MapUtil.newMap().put("ST", ticket));
        return HttpUtils.doRequest(request, SSOUser.class);
    }
}
