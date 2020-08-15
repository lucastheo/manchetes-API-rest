package com.example.demo.service;

import com.example.demo.repository.MostCitedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
            return mostCitedRepository.getAll( );
        }
    }

    public HashMap<String, Set<LocalDateTime>> getAllUrlsAndData(){
        return mostCitedRepository.getAllUrlsAndData();
    }

    public Set<String> getAllUrls(){
        return mostCitedRepository.getAllUrlsAndData().keySet();
    }

    public Set<LocalDateTime> getAllDates(){
        HashMap<String, Set<LocalDateTime>> keys = mostCitedRepository.getAllUrlsAndData();
        Set<LocalDateTime> out = new HashSet<>();
        for( String url : keys.keySet() ){
            for( LocalDateTime date : keys.get( url ) ){
                out.add(date);
            }
        }
        return out;
    }


    private LocalDateTime toLocalDate(CharSequence date ){
        return  LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME );
    }
}
