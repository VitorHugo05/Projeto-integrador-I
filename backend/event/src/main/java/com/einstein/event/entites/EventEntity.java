package com.einstein.event.entites;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_event")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;

    private LocalDateTime inscriptionStartTime;
    private LocalDateTime inscriptionEndTime;

    private String location;

    private String code;

    @ManyToOne
    @JoinColumn(name = "coordinator_id", nullable = false)
    private CoordinatorEntity coordinator;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<StudentPresenceEntity> studentPresenceList = new ArrayList<>();

    public EventEntity() {
    }

    public EventEntity(Long id, String title, String description, LocalTime startTime, LocalTime endTime, LocalDate date, LocalDateTime inscriptionStartTime, LocalDateTime inscriptionEndTime, String location, String code, CoordinatorEntity coordinator, List<StudentPresenceEntity> studentPresenceList) {
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

    public List<StudentPresenceEntity> getStudentPresenceList() {
        return studentPresenceList;
    }

    public CoordinatorEntity getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(CoordinatorEntity coordinator) {
        this.coordinator = coordinator;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDateTime getInscriptionEndTime() {
        return inscriptionEndTime;
    }

    public void setInscriptionEndTime(LocalDateTime inscriptionEndTime) {
        this.inscriptionEndTime = inscriptionEndTime;
    }

    public LocalDateTime getInscriptionStartTime() {
        return inscriptionStartTime;
    }

    public void setInscriptionStartTime(LocalDateTime inscriptionStartTime) {
        this.inscriptionStartTime = inscriptionStartTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
