package com.einstein.event.dtos.request;

public class CourseRequestDto {

    private String name;
    private String coordinatorCpf;

    public CourseRequestDto() {
    }

    public CourseRequestDto(String name, String coordinatorCpf) {
        this.name = name;
        this.coordinatorCpf = coordinatorCpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinatorCpf() {
        return coordinatorCpf;
    }

    public void setCoordinatorCpf(String coordinatorCpf) {
        this.coordinatorCpf = coordinatorCpf;
    }
}
