package com.einstein.event.controllers;

import com.einstein.event.dtos.request.InscriptionRequestDto;
import com.einstein.event.dtos.response.InscriptionResponseDto;
import com.einstein.event.entites.StudentPresenceEntity;
import com.einstein.event.mapper.PresenceDtoMapper;
import com.einstein.event.services.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/presence")
public class PresenceController {
    private final PresenceService presenceService;
    private final PresenceDtoMapper presenceDtoMapper;

    @Autowired
    public PresenceController(PresenceService presenceService, PresenceDtoMapper presenceDtoMapper) {
        this.presenceService = presenceService;
        this.presenceDtoMapper = presenceDtoMapper;
    }

    @PostMapping("/inscription")
    public ResponseEntity<Void> inscription(@RequestBody InscriptionRequestDto inscriptionRequestDto) {
        StudentPresenceEntity presenceEntity = presenceService.insert(inscriptionRequestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(presenceEntity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/event")
    public ResponseEntity<List<InscriptionResponseDto>> findAllByEventId(@RequestParam Long eventId) {
        List<StudentPresenceEntity> presenceEntityList = presenceService.findAllByEventId(eventId);
        List<InscriptionResponseDto> inscriptionResponseDtoList = presenceEntityList.stream()
                .map(presenceDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(inscriptionResponseDtoList);
    }

    @GetMapping("/student")
    public ResponseEntity<List<InscriptionResponseDto>> findAllByStudentRa(@RequestParam String studentRa) {
        List<StudentPresenceEntity> presenceEntityList = presenceService.findAllByStudentRa(studentRa);
        List<InscriptionResponseDto> inscriptionResponseDtoList = presenceEntityList.stream()
                .map(presenceDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(inscriptionResponseDtoList);
    }
}
