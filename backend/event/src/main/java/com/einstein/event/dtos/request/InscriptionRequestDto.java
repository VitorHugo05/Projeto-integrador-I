package com.einstein.event.dtos.request;

public class InscriptionRequestDto {
    private String ra;
    private Long eventId;

    public InscriptionRequestDto(String ra, Long eventId) {
        this.ra = ra;
        this.eventId = eventId;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
