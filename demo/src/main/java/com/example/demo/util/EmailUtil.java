package com.example.demo.util;

public class EmailUtil {
    public static String EMAIL_REGEX = "[a-zA-Z0-9-._]*@[a-zA-Z0-9-._]*";
    public static Boolean validation( String email ){

        return email.matches(EMAIL_REGEX );
    }
}
