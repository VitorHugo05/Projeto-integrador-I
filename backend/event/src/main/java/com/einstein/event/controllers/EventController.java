package com.einstein.event.controllers;

import com.einstein.event.dtos.request.EventRequestDto;
import com.einstein.event.dtos.response.EventResponseDto;
import com.einstein.event.entites.EventEntity;
import com.einstein.event.mapper.EventDtoMapper;
import com.einstein.event.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController {
    private final EventService eventService;
    private final EventDtoMapper eventDtoMapper;

    public EventController(EventService eventService, EventDtoMapper eventDtoMapper) {
        this.eventService = eventService;
        this.eventDtoMapper = eventDtoMapper;
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody EventRequestDto eventRequestDto) {
        EventEntity eventEntity = eventDtoMapper.toEntity(eventRequestDto);
        eventEntity = eventService.insert(eventEntity, eventRequestDto.getCoordinatorCpf());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(eventEntity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> findAll(){
        List<EventEntity> eventEntityList = eventService.findAll();
        List<EventResponseDto> eventResponseDtoList = eventEntityList.stream()
                .map(eventDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(eventResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> findById(@PathVariable Long id){
        EventEntity eventEntity = eventService.findById(id);
        EventResponseDto eventResponseDto = eventDtoMapper.toResponse(eventEntity);
        return ResponseEntity.ok().body(eventResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody EventRequestDto eventRequestDto) {
        eventService.update(eventRequestDto, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
