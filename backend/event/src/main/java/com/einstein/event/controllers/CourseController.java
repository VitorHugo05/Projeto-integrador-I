package com.einstein.event.controllers;

import com.einstein.event.dtos.request.CourseRequestDto;
import com.einstein.event.dtos.response.CourseResponseDto;
import com.einstein.event.entites.CourseEntity;
import com.einstein.event.mapper.CourseDtoMapper;
import com.einstein.event.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;
    private final CourseDtoMapper courseDtoMapper;

    public CourseController(CourseService courseService, CourseDtoMapper courseDtoMapper) {
        this.courseService = courseService;
        this.courseDtoMapper = courseDtoMapper;
    }

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
        List<CourseResponseDto> courseResponseDtoList = courseEntity.stream()
                .map(courseDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(courseResponseDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CourseRequestDto courseRequestDto){
        courseService.update(id, courseRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
