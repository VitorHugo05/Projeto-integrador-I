package com.einstein.event.services;

import com.einstein.event.dtos.request.EventRequestDto;
import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.entites.EventEntity;
import com.einstein.event.mapper.EventDtoMapper;
import com.einstein.event.repositories.EventRepository;
import com.einstein.event.services.exceptions.BadRequestArgumentException;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CoordinatorService coordinatorService;
    private final EventDtoMapper eventDtoMapper;

    public EventService(EventRepository eventRepository, CoordinatorService coordinatorService, EventDtoMapper eventDtoMapper) {
        this.eventRepository = eventRepository;
        this.coordinatorService = coordinatorService;
        this.eventDtoMapper = eventDtoMapper;
    }

    public EventEntity insert(EventEntity eventEntity, String coordinatorCpf) {
        LocalDateTime now = LocalDateTime.now();

        if (eventEntity.getDate().atTime(eventEntity.getStartTime()).isBefore(now)) {
            throw new BadRequestArgumentException("O evento não pode começar antes da data atual.");
        }

        if (eventEntity.getEndTime().isBefore(eventEntity.getStartTime())) {
            throw new BadRequestArgumentException("O evento não pode terminar antes de começar.");
        }

        if (Duration.between(eventEntity.getStartTime(), eventEntity.getEndTime()).toMinutes() < 30) {
            throw new BadRequestArgumentException("O evento deve ter pelo menos 30 minutos de duração.");
        }

        if (eventEntity.getInscriptionEndTime().isBefore(eventEntity.getInscriptionStartTime())) {
            throw new BadRequestArgumentException("O período de inscrição não pode terminar antes de começar.");
        }

        if (Duration.between(eventEntity.getInscriptionStartTime(), eventEntity.getInscriptionEndTime()).toDays() < 1) {
            throw new BadRequestArgumentException("O período de inscrição deve durar pelo menos 1 dia.");
        }

        CoordinatorEntity coordinatorEntity = coordinatorService.findByCpf(coordinatorCpf);
        eventEntity.setCoordinator(coordinatorEntity);

        return eventRepository.save(eventEntity);
    }


    public List<EventEntity> findAll() {
        return eventRepository.findAll();
    }

    public EventEntity findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Event not found"));
    }

    public void update(EventRequestDto eventRequestDto, Long id) {
        findById(id);
        EventEntity eventEntity = eventDtoMapper.toEntity(eventRequestDto);
        eventEntity.setId(id);
        eventRepository.save(eventEntity);
    }

    public void delete(Long id) {
        findById(id);
        eventRepository.deleteById(id);
    }
}
