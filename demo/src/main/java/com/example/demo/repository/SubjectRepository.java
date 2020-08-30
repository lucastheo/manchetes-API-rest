package com.example.demo.repository;

import com.example.demo.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectRepository {
    private static String pathBase = "/home/theodoro/Documents/Projetos/manchetes/data/";
    private static String pathSubject = "json_refined/subject/all_url_all_data.json";

    public CharSequence getAll(){
        return FileUtil.openFile( pathBase + pathSubject );
    }
}
