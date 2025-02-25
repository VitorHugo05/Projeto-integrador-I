package com.einstein.event.dtos.request;

public class RectorRequestDto {
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private String password;

    public RectorRequestDto() {
    }

    public RectorRequestDto(String name, String cpf, String phone, String email, String password) {
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
