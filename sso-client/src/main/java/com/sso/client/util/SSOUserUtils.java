package com.sso.client.util;


import com.sso.client.entity.SSOUser;

/**
 * Created by mjd on 2020/4/17 09:54
 */
public class SSOUserUtils {

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
