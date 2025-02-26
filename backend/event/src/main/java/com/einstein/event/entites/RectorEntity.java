package com.einstein.event.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_rector")
public class RectorEntity extends UserEntity {

    private String name;
    private String cpf;
    private String phone;

    public RectorEntity() {
        this.setRole(UserRole.RECTOR);
    }

    public RectorEntity(Long id, String email, String password, String name, String cpf, String phone) {
        super(id, email, password, UserRole.RECTOR);
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
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
}
