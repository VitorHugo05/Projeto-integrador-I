package com.einstein.event.dtos.response;
import com.einstein.event.entites.StudentPresenceEntity;
import java.util.List;

public class StudentResponseDto {
    private Long id;
    private String ra;

    private String name;
    private String email;
    private String phone;
    private StudentCourseResponseDto course;
    private List<InscriptionResponseDto> presences;

    public StudentResponseDto() {
    }

    public StudentResponseDto(Long id, String ra, String name, String email, String phone, StudentCourseResponseDto course, List<InscriptionResponseDto> presences) {
        this.id = id;
        this.ra = ra;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.course = course;
        this.presences = presences;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StudentCourseResponseDto getCourse() {
        return course;
    }

    public void setCourse(StudentCourseResponseDto course) {
        this.course = course;
    }

    public List<InscriptionResponseDto> getPresences() {
        return presences;
    }

    public void setPresences(List<InscriptionResponseDto> presences) {
        this.presences = presences;
    }
}
