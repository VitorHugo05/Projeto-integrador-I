package com.einstein.event.services;

import com.einstein.event.dtos.request.EventRequestDto;
import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.entites.EventEntity;
import com.einstein.event.mapper.EventDtoMapper;
import com.einstein.event.repositories.EventRepository;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

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
