package com.mf.merchants.security;

public class AccessContext {
    private static final ThreadLocal<String> token = new ThreadLocal<>();
    public static String getToken () {
        return token.get();
    }
    public static void setToken (String tokenStr) {
        token.set(tokenStr);
    }

    public static void clearAccessKey () {
        token.remove();
    }
}
