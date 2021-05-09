package com.example.demo.util;

public class ZqbPublicUtil {
    public static boolean strEmpty(String... args) {
        for (String str : args) {
            if (null == str || "".equals(str) || "null".equals(str)) {
                return true;
            }
        }
        return false;
    }
}
