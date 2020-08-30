package com.example.demo.controler;

import com.example.demo.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/data/query")
public class SubjectControler {
    @Autowired
    SubjectService subjectService;
    @GetMapping("/subject")
    public ResponseEntity getMostCitedBy(){
        return ResponseEntity.ok(subjectService.getAll());
    }

}
