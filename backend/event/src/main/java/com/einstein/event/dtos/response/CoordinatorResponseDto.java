package com.einstein.event.dtos.response;

import com.einstein.event.entites.CourseEntity;

public class CoordinatorResponseDto {
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private CourseEntity course;

    public CoordinatorResponseDto() {
    }

    public CoordinatorResponseDto(String name, String cpf, String phone, String email, CourseEntity course) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
        this.course = course;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
