package com.example.demo.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
    public static CharSequence openFile( CharSequence file ){
        FileReader fileReader;
        boolean noException = true;
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
}
