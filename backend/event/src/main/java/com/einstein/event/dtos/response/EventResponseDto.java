package com.einstein.event.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class EventResponseDto {

    private Long id;

    private String title;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime inscriptionStartTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime inscriptionEndTime;

    private String location;

    private String code;

    private CoordinatorCourseDto coordinator;

    @JsonIgnoreProperties(value = {"eventId", "title", "description", "startTime", "endTime", "date"})
    private List<InscriptionResponseDto> studentPresenceList;

    public EventResponseDto() {
    }

    public EventResponseDto(Long id, String title, String description, LocalTime startTime, LocalTime endTime, LocalDate date, LocalDateTime inscriptionStartTime, LocalDateTime inscriptionEndTime, String location, String code, CoordinatorCourseDto coordinator, List<InscriptionResponseDto> studentPresenceList) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.inscriptionStartTime = inscriptionStartTime;
        this.inscriptionEndTime = inscriptionEndTime;
        this.location = location;
        this.code = code;
        this.coordinator = coordinator;
        this.studentPresenceList = studentPresenceList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getInscriptionStartTime() {
        return inscriptionStartTime;
    }

    public void setInscriptionStartTime(LocalDateTime inscriptionStartTime) {
        this.inscriptionStartTime = inscriptionStartTime;
    }

    public LocalDateTime getInscriptionEndTime() {
        return inscriptionEndTime;
    }

    public void setInscriptionEndTime(LocalDateTime inscriptionEndTime) {
        this.inscriptionEndTime = inscriptionEndTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CoordinatorCourseDto getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(CoordinatorCourseDto coordinator) {
        this.coordinator = coordinator;
    }

    public List<InscriptionResponseDto> getStudentPresenceList() {
        return studentPresenceList;
    }

    public void setStudentPresenceList(List<InscriptionResponseDto> studentPresenceList) {
        this.studentPresenceList = studentPresenceList;
    }
}
