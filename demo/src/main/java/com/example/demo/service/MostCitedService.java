package com.example.demo.service;

import com.example.demo.repository.MostCitedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class MostCitedService {

    @Autowired
    private MostCitedRepository mostCitedRepository;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("");

    public CharSequence get( Optional<CharSequence > url, Optional<CharSequence> date ){
        if( url.isPresent() && date.isPresent() ){
            return mostCitedRepository.getByUrlAndDate( url.get() , toLocalDate( date.get() ) );
        }else if( url.isPresent() && date.isPresent() == false ){
            return mostCitedRepository.getByUrl( url.get() );
        }else if( url.isPresent() == false && date.isPresent() ){
            return mostCitedRepository.getByDate( toLocalDate( date.get() ));
        }else {
            return "TODO";
        }
    }

    private LocalDateTime toLocalDate(CharSequence date ){
        return  LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME );
    }
}
