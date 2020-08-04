package com.example.demo.Validation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DataValidation {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("");

    public static Boolean mostCited( String data ){
        try {
            LocalDateTime localdate = LocalDateTime.parse(data, DateTimeFormatter.ISO_LOCAL_DATE_TIME );
            if( localdate.isBefore( LocalDateTime.now() ) ) {
                return true;
            }
        }catch ( Exception e ){
            System.err.println("Erro na conversão" + e.getMessage() );
        }
        return false;
    }
}
