package com.einstein.event.controllers;

import com.einstein.event.dtos.request.CourseRequestDto;
import com.einstein.event.dtos.response.CourseResponseDto;
import com.einstein.event.entites.CourseEntity;
import com.einstein.event.mapper.CourseDtoMapper;
import com.einstein.event.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseDtoMapper courseDtoMapper;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody CourseRequestDto courseRequestDto){
        CourseEntity courseEntity = courseDtoMapper.toEntity(courseRequestDto);
        CourseEntity courseResponse = courseService.insert(courseEntity, courseRequestDto.getCoordinatorCpf());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(courseResponse.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDto> findById(@PathVariable Long id){
        CourseEntity courseEntity = courseService.findById(id);
        CourseResponseDto courseResponseDto = courseDtoMapper.toResponse(courseEntity);
        return ResponseEntity.ok().body(courseResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> findAll(){
        List<CourseEntity> courseEntity = courseService.findAll();
        List<CourseResponseDto> courseResponseDtos = courseEntity.stream()
                .map(courseEntity1 -> courseDtoMapper.toResponse(courseEntity1))
                .toList();
        return ResponseEntity.ok().body(courseResponseDtos);
    }
}
