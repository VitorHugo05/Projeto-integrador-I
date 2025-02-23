package com.einstein.event.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

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

    public CourseEntity() {
    }

    public CourseEntity(Long id, String name, CoordinatorEntity coordinator) {
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

    public CoordinatorEntity getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(CoordinatorEntity coordinator) {
        this.coordinator = coordinator;
    }
}
