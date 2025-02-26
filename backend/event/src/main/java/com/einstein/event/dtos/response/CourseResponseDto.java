package com.einstein.event.dtos.response;

import com.einstein.event.entites.StudentEntity;

import java.util.List;

public class CourseResponseDto {

    private Long id;
    private String name;
    private CoordinatorCourseDto coordinator;
    private List<StudentEntity> students;

    public CourseResponseDto() {
    }

    public CourseResponseDto(Long id, String name, CoordinatorCourseDto coordinator, List<StudentEntity> students) {
        this.id = id;
        this.name = name;
        this.coordinator = coordinator;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CoordinatorCourseDto getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(CoordinatorCourseDto coordinator) {
        this.coordinator = coordinator;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }
}
