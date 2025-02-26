package com.einstein.event.entites;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_coordinator")
public class CoordinatorEntity extends UserEntity {

    private String name;
    private String cpf;
    private String phone;

    @OneToOne(mappedBy = "coordinator", cascade = CascadeType.ALL)
    private CourseEntity course;

    @OneToMany(mappedBy = "coordinator", cascade = CascadeType.ALL)
    private List<EventEntity> events = new ArrayList<>();

    public CoordinatorEntity() {
        this.setRole(UserRole.COORDINATOR);
    }

    public CoordinatorEntity(Long id, String email, String password, String name, String cpf, String phone, CourseEntity course, List<EventEntity> events) {
        super(id, email, password, UserRole.COORDINATOR);
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.course = course;
        this.events = events;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public Long getId() {
        return super.getId();
    }

    public void setId(Long id) {
        super.setId(id);
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

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public List<EventEntity> getEvents() {
        return events;
    }
}
