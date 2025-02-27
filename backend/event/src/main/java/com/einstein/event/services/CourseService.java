package com.einstein.event.services;

import com.einstein.event.dtos.request.CourseRequestDto;
import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.entites.CourseEntity;
import com.einstein.event.mapper.CourseDtoMapper;
import com.einstein.event.repositories.CourseRepository;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CoordinatorService coordinatorService;
    private final CourseDtoMapper courseDtoMapper;

    public CourseService(CourseRepository courseRepository, CoordinatorService coordinatorService, CourseDtoMapper courseDtoMapper) {
        this.courseRepository = courseRepository;
        this.coordinatorService = coordinatorService;
        this.courseDtoMapper = courseDtoMapper;
    }

    public CourseEntity insert(CourseEntity courseEntity, String cpf) {
        CoordinatorEntity coordinatorEntity = coordinatorService.findByCpf(cpf);
        courseEntity.setCoordinator(coordinatorEntity);
        return courseRepository.save(courseEntity);
    }

    public CourseEntity findById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Course not found"));
    }

    public List<CourseEntity> findAll() {
        return courseRepository.findAll();
    }

    public void update(Long id, CourseRequestDto courseRequestDto) {
        findById(id);
        CourseEntity courseEntity = courseDtoMapper.toEntity(courseRequestDto);
        courseEntity.setId(id);
        courseRepository.save(courseEntity);
    }

    public void delete(Long id) {
        findById(id);
        courseRepository.deleteById(id);
    }

    public CourseEntity findByName(String course) {
        return courseRepository.findByName(course).orElseThrow(() -> new ObjectNotFoundException("Course not found"));
    }
}
