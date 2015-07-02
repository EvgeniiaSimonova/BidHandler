package com.company.tools;

import java.io.UnsupportedEncodingException;

public class Encoder {

    private static final String CHARSET_NAME = "UTF-8";

    public static String convert(String input) throws UnsupportedEncodingException {
        byte[] bytes = input.getBytes(CHARSET_NAME);
        return new String(bytes, CHARSET_NAME);
    }

}
