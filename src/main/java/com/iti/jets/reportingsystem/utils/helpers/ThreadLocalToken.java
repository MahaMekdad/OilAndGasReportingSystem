package com.iti.jets.reportingsystem.utils.helpers;

public class ThreadLocalToken {

    private static final ThreadLocal<ThreadLocalToken> instance = new ThreadLocal<>();

    private final String token;

    private ThreadLocalToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public static ThreadLocalToken create(String token) {
        ThreadLocalToken threadLocalToken = new ThreadLocalToken(token);
        instance.set(threadLocalToken);
        return threadLocalToken;
    }

    public static ThreadLocalToken getCurrentInstance() {
        return instance.get();
    }

    public void close() {
        instance.remove();
    }
}
