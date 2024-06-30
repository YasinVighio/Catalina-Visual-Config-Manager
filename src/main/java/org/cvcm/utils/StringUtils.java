package org.cvcm.utils;

public class StringUtils {
    public static boolean isStringValid(String s){
        return s != null && !s.isEmpty() && !s.isBlank();
    }
}
