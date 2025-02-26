package com.einstein.event.dtos.request;


public class StudentRequestDto {
    private String name;
    private String phone;
    private String email;
    private String password;
    private String ra;
    private String course;

    public StudentRequestDto() {
    }

    public StudentRequestDto(String name, String phone, String email, String password, String ra, String course) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.ra = ra;
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
