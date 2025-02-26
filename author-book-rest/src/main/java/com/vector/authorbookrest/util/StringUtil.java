package com.vector.authorbookrest.util;

public class StringUtil {

    public static String lastWord(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        if (value.equals(" ")){
            throw new RuntimeException("value cant be space");
        }
        if (!value.contains(" ")) {
            return value;
        }
        String[] words = value.split(" ");
        return words[words.length - 1];
    }
}
