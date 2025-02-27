package com.einstein.event.services;

import com.einstein.event.dtos.request.InscriptionRequestDto;
import com.einstein.event.entites.EventEntity;
import com.einstein.event.entites.StudentPresenceEntity;
import com.einstein.event.repositories.PresenceRepository;
import com.einstein.event.services.exceptions.DataAlreadyExistException;
import com.einstein.event.services.exceptions.BadRequestArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PresenceService {

    private final PresenceRepository presenceRepository;
    private final EventService eventService;
    private final StudentService studentService;

    @Autowired
    public PresenceService(PresenceRepository presenceRepository, EventService eventService, StudentService studentService) {
        this.presenceRepository = presenceRepository;
        this.eventService = eventService;
        this.studentService = studentService;
    }

    public StudentPresenceEntity insert(InscriptionRequestDto inscriptionRequestDto) {
        if (presenceRepository.existsByStudentRaAndEventId(inscriptionRequestDto.getRa(), inscriptionRequestDto.getEventId())) {
            throw new DataAlreadyExistException("Student registered for this event");
        }
        EventEntity eventEntity = eventService.findById(inscriptionRequestDto.getEventId());
        if (LocalDateTime.now().isAfter(eventEntity.getInscriptionStartTime()) &&
                LocalDateTime.now().isBefore(eventEntity.getInscriptionEndTime())) {
            StudentPresenceEntity presenceEntity = new StudentPresenceEntity();
            presenceEntity.setEvent(eventEntity);
            presenceEntity.setStudent(studentService.findByRa(inscriptionRequestDto.getRa()));
            return presenceRepository.save(presenceEntity);
        } else {
            throw new BadRequestArgumentException("Registration time is up");
        }
    }

    public List<StudentPresenceEntity> findAllByEventId(Long id) {
        return presenceRepository.findAllByEventId(id);
    }

    public List<StudentPresenceEntity> findAllByStudentRa(String ra) {
        return presenceRepository.findAllByStudentRa(ra);
    }
}
