package com.einstein.event.entites;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_student")
public class StudentEntity extends UserEntity {

    private String name;
    private String ra;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<StudentPresenceEntity> presences = new ArrayList<>();

    public StudentEntity() {
    }

    public StudentEntity(Long id, String email, String password, String name, String ra, String phone, CourseEntity course, List<StudentPresenceEntity> presences) {
        super(id, email, password, UserRole.STUDENT);
        this.name = name;
        this.ra = ra;
        this.phone = phone;
        this.course = course;
        this.presences = presences;
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

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
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

    public List<StudentPresenceEntity> getPresences() {
        return presences;
    }
}
