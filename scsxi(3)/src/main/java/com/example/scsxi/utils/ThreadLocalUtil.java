package com.example.scsxi.utils;

public class ThreadLocalUtil {
    private static final ThreadLocal threadLocal = new ThreadLocal();

    public  static <T> T get() {
        return (T) threadLocal.get();
    }
    public static void set(Object obj) {
        threadLocal.set(obj);
    }
    public static void remove() {
        threadLocal.remove();
    }
}
