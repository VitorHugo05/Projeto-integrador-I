package com.einstein.event.services;

import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.entites.EventEntity;
import com.einstein.event.repositories.EventRepository;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final CoordinatorService coordinatorService;

    public EventService(EventRepository eventRepository, CoordinatorService coordinatorService) {
        this.eventRepository = eventRepository;
        this.coordinatorService = coordinatorService;
    }

    public EventEntity insert(EventEntity eventEntity, Long coordinatorId) {
        CoordinatorEntity coordinatorEntity = coordinatorService.findById(coordinatorId);
        eventEntity.setCoordinator(coordinatorEntity);
        return eventRepository.save(eventEntity);
    }

    public List<EventEntity> findAll() {
        return eventRepository.findAll();
    }

    public EventEntity findById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Event not found"));
    }
}
