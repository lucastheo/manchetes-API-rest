package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Repository
public class MostCitedRepository {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyu-MM-dd-HH");
    private static final String pathAll = "json_refined/most_cited/all_url_all_data.json";
    private static final String pathData = "json_refined/most_cited/all_url_by_data/__data__.json";
    private static final String pathUrl = "json_refined/most_cited/by_url_all_data/__url__.json";
    private static final String pathUrlAndData = "json_refined/most_cited/by_url_by_data/__url__-__data__.json";

    public CharSequence getByUrl( CharSequence url ){
        return openFile( getPathByUrl( url ));
    }
    public CharSequence getByDate( LocalDateTime date ){
        return openFile( getPathByData( date ));
    }
    public CharSequence getByUrlAndDate( CharSequence url , LocalDateTime date){
        return openFile( getPathByUrlAndDate( url , date ));
    }

    private CharSequence openFile( CharSequence file ){
        FileReader fileReader;
        Boolean noException = true;
        try { fileReader = new FileReader(file.toString());}
        catch ( FileNotFoundException e ){ System.err.println("MostCitedRepository: Erro ao encontrar o arquivo :" + file ); return "";}

        BufferedReader br = new BufferedReader(fileReader);
        StringBuilder linha = new StringBuilder();
        try {
            while(br.ready()){
                 linha.append(br.readLine() );
            }
        }
        catch (IOException e ){ System.err.println("MostCitedRepoisitory: Erro ao ler o arquivo :" + file ); noException = false;}

        try { br.close(); }
        catch ( IOException e ){ System.out.println("MostCitedRepository: Erro ao finalizar o buffer");}

        if( noException ){
            return linha.toString();
        }
        return  "";
    }

    private CharSequence toUrl( CharSequence data ){
        return  data.toString().replace(":","-").replace("/", "_");
    }
    private CharSequence toDate( LocalDateTime date ){
        return dateTimeFormatter.format(date);
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
