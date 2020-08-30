package com.example.demo.repository;

import com.example.demo.util.FileUtil;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class MostCitedRepository {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyu-MM-dd-HH");
    private static final DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
    //private static final String pathBase =  "/home/lucathe/Documents/Projetos/manchetes/data/";
    private static final String pathBase =  "/home/theodoro/Documents/Projetos/manchetes/data/";
    private static final String pathAll = pathBase + "json_refined/most_cited/all_url_all_data.json";
    private static final String pathData = pathBase + "json_refined/most_cited/all_url_by_data/__data__.json";
    private static final String pathUrl = pathBase +"json_refined/most_cited/by_url_all_data/__url__.json";
    private static final String pathUrlAndData = pathBase +"json_refined/most_cited/by_url_by_data/__url__-__data__.json";
    private static final String pathFatherUrlAndData = pathBase +"json_refined/most_cited/by_url_by_data/";

    public CharSequence getByUrl( CharSequence url ){
        return FileUtil.openFile( getPathByUrl( url ));
    }
    public CharSequence getByDate( LocalDateTime date ){
        return FileUtil.openFile( getPathByData( date ));
    }
    public CharSequence getByUrlAndDate( CharSequence url , LocalDateTime date){
        return FileUtil.openFile( getPathByUrlAndDate( url , date ));
    }
    public CharSequence getAll(){ return FileUtil.openFile( pathAll ); };

    public HashMap<String, Set<LocalDateTime>> getAllUrlsAndData(){
        File file = new File(pathFatherUrlAndData );
        HashMap<String, Set<LocalDateTime>> charSequences = new HashMap<>();
        String name, url, anoStr[];
        LocalDateTime ano;
        for( File children : file.listFiles() ){
            name = children.getName();
            anoStr = name.substring( name.length() -  18 , name.length() - 5 ).split("-");
            ano = LocalDateTime.of( Integer.parseInt(anoStr[ 0 ] ) ,  Integer.parseInt( anoStr[ 1 ] ) ,  Integer.parseInt( anoStr[ 2 ] ) ,  Integer.parseInt( anoStr[ 3 ] ), 0  );
            url = name.substring(0 , name.length() - 19 ).replace("-",":").replace("_", "/");

            if( charSequences.containsKey( url ) == false ){
                charSequences.put( url , new HashSet());
            }
            charSequences.get( url ).add( ano );

        }
        return charSequences;
    }

    private CharSequence toUrl( CharSequence data ){
        return  data.toString().replace(":","-").replace("/", "_");
    }
    private CharSequence toDate( LocalDateTime date ){
        return dateTimeFormatter2.format(date);
    }
    private CharSequence toUrlAndDate( CharSequence url , LocalDateTime date ){
        return toUrl( url )+ "-" + toDate(date);
    }
    private CharSequence getPathByUrl( CharSequence url ){
        return pathUrl.replace("__url__" , toUrl( url ));
    }
    private CharSequence getPathByData( LocalDateTime date ){
        return pathData.replace("__data__" , toDate( date ));
    }
    private CharSequence getPathByUrlAndDate( CharSequence url , LocalDateTime date){
        return pathUrlAndData.replace("__url__" , toUrl( url )).replace("__data__" , toDate( date ) );
    }
}
