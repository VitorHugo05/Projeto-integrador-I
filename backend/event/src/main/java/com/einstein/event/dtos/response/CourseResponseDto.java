package com.einstein.event.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class CourseResponseDto {

    private Long id;
    private String name;
    private CoordinatorCourseDto coordinator;

    @JsonIgnoreProperties(value = {"course", "presences"})
    private List<StudentResponseDto> students;

    public CourseResponseDto() {
    }

    public CourseResponseDto(Long id, String name, CoordinatorCourseDto coordinator, List<StudentResponseDto> students) {
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

    public List<StudentResponseDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentResponseDto> students) {
        this.students = students;
    }
}
