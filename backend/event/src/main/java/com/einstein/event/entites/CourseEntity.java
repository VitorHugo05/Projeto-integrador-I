package com.einstein.event.entites;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_course")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "coordinator_id", unique = true, nullable = false)
    private CoordinatorEntity coordinator;

    @OneToMany(mappedBy = "course")
    private List<StudentEntity> students;

    public CourseEntity() {
    }

    public CourseEntity(Long id, String name, CoordinatorEntity coordinator, List<StudentEntity> students) {
        this.id = id;
        this.name = name;
        this.coordinator = coordinator;
        this.students = students;
    }

    public List<StudentEntity> getStudents() {
        return students;
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

    public CoordinatorEntity getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(CoordinatorEntity coordinator) {
        this.coordinator = coordinator;
    }
}
