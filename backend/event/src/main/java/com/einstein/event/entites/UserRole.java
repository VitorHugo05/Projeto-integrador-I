package com.einstein.event.entites;

public enum UserRole {
    COORDINATOR("Coordinator"),
    RECTOR("Rector"),
    STUDENT("Student");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
