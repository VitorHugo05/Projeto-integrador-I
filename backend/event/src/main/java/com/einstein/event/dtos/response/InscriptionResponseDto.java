package com.einstein.event.dtos.response;


import java.time.LocalDate;
import java.time.LocalTime;

public class InscriptionResponseDto {
    private String ra;

    private Long eventId;
    private String title;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;

    public InscriptionResponseDto(String ra, Long eventId, String title, String description, LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.ra = ra;
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
