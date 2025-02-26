package com.einstein.event.services;

import com.einstein.event.entites.StudentEntity;
import com.einstein.event.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentEntity insert(StudentEntity studentEntity) {
        return null;
    }
}
