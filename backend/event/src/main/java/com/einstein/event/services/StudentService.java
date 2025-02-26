package com.einstein.event.services;

import com.einstein.event.dtos.request.StudentRequestDto;
import com.einstein.event.dtos.request.StudentUpdateRequestDto;
import com.einstein.event.entites.StudentEntity;
import com.einstein.event.mapper.StudentDtoMapper;
import com.einstein.event.repositories.StudentRepository;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentDtoMapper studentDtoMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CourseService courseService;

    public StudentEntity insert(StudentRequestDto studentRequestDto) {
        StudentEntity studentEntity = studentDtoMapper.toEntity(studentRequestDto);
        studentEntity.setPassword(passwordEncoder.encode(studentRequestDto.getPassword()));
        studentEntity.setCourse(courseService.findByName(studentRequestDto.getCourse()));
        return studentRepository.save(studentEntity);
    }

    public List<StudentEntity> findAll() {
        return studentRepository.findAll();
    }

    public StudentEntity findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Student not found"));
    }

    public void update(Long id, StudentUpdateRequestDto studentRequestDto) {
        findById(id);
        StudentEntity studentEntity = studentDtoMapper.toEntity(studentRequestDto);
        studentEntity.setId(id);
        studentRepository.save(studentEntity);
    }

    public void delete(Long id) {
        findById(id);
        studentRepository.deleteById(id);
    }
}
