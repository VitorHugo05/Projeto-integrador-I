package com.einstein.event.dtos.response;

public class StudentPresenceResponseDto {
    private String ra;

    public StudentPresenceResponseDto() {
    }

    public StudentPresenceResponseDto(String ra) {
        this.ra = ra;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
