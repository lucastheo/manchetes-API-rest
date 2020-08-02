package com.example.demo.Validation;

public class UrlValidation {

    private static final String URL_REGEX = "[a-zA-Z0-9.-//]*";
    public static Boolean urlMostCited( String url ){
        return  url.matches(URL_REGEX);
    }
}
