package com.einstein.event.dtos.response;

public class StudentCourseResponseDto {
    private Long id;
    private String name;
    private CoordinatorCourseDto coordinator;

    public StudentCourseResponseDto() {
    }

    public StudentCourseResponseDto(Long id, String name, CoordinatorCourseDto coordinator) {
        this.id = id;
        this.name = name;
        this.coordinator = coordinator;
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
}
