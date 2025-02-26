package com.einstein.event.controllers;

import com.einstein.event.dtos.request.StudentUpdateRequestDto;
import com.einstein.event.dtos.response.StudentResponseDto;
import com.einstein.event.entites.StudentEntity;
import com.einstein.event.mapper.StudentDtoMapper;
import com.einstein.event.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;
    private final StudentDtoMapper studentDtoMapper;

    public StudentController(StudentService studentService, StudentDtoMapper studentDtoMapper) {
        this.studentService = studentService;
        this.studentDtoMapper = studentDtoMapper;
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> findAll() {
        List<StudentEntity> studentEntityList = studentService.findAll();
        List<StudentResponseDto> studentResponseDtoList = studentEntityList.stream()
                .map(studentDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(studentResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> findById(@PathVariable Long id) {
        StudentEntity studentEntity = studentService.findById(id);
        StudentResponseDto studentResponseDto = studentDtoMapper.toResponse(studentEntity);
        return ResponseEntity.ok().body(studentResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody StudentUpdateRequestDto studentRequestDto) {
        studentService.update(id, studentRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
