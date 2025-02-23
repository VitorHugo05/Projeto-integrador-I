package com.einstein.event.entites;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_student_presence")
public class StudentPresenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;

    private Boolean isPresent = false;

    public StudentPresenceEntity() {
    }

    public StudentPresenceEntity(Long id, StudentEntity student, EventEntity event, Boolean isPresent) {
        this.id = id;
        this.student = student;
        this.event = event;
        this.isPresent = isPresent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public void setPresent(Boolean present) {
        isPresent = present;
    }
}
