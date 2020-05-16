package com.sso.client.util;


import com.awesome.util.http.HttpMethod;
import com.awesome.util.http.HttpRequest;
import com.awesome.util.http.HttpUtils;
import com.awesome.util.util.MapUtil;
import com.sso.client.entity.SSOUser;

/**
 * Created by mjd on 2020/4/17 09:54
 */
public class SSOUserUtils {
    /**
     * 根据票据获取 SSO 用户信息
     */
    public static SSOUser getUser(String ticket) {
        HttpRequest request = new HttpRequest();
        request.setUrl("http://localhost:10000/sso/get_user_by_ST");
        request.setMethod(HttpMethod.GET);
        request.setParams(MapUtil.newMap().put("ST", ticket));
        return HttpUtils.doRequest(request, SSOUser.class);
    }

    private SSOUserUtils() {
    }

    private static final ThreadLocal<SSOUser> threadLocal = new ThreadLocal<>();

    public static void setUser(SSOUser user) {
        threadLocal.set(user);
    }

    public static SSOUser getUser() {
        return threadLocal.get();
    }

    public static int getUid() {
        return getUser().getId();
    }

    public static String getUserName() {
        return getUser().getName();
    }

    public static void clear() {
        threadLocal.remove();
    }

}
