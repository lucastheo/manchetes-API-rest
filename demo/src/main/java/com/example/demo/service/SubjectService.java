package com.example.demo.service;

import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    public CharSequence getAll(){
        return subjectRepository.getAll();
    }
}
