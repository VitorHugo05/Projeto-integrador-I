package com.einstein.event.controllers;

import com.einstein.event.dtos.request.CoordinatorRequestDto;
import com.einstein.event.dtos.response.CoordinatorResponseDto;
import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.mapper.CoordinatorDtoMapper;
import com.einstein.event.services.AuthorizationService;
import com.einstein.event.services.CoordinatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/coordinator")
public class CoordinatorController {

    @Autowired
    private CoordinatorService coordinatorService;

    @Autowired
    private CoordinatorDtoMapper coordinatorDtoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody CoordinatorRequestDto coordinatorRequestDto) {
        if(authorizationService.validateEmail(coordinatorRequestDto.getEmail())) {
            throw new IllegalArgumentException("Email já utilizado.");
        }

        CoordinatorEntity coordinatorEntity = coordinatorDtoMapper.toEntity(coordinatorRequestDto);
        coordinatorEntity.setPassword(passwordEncoder.encode(coordinatorRequestDto.getPassword()));
        coordinatorEntity = coordinatorService.insert(coordinatorEntity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(coordinatorEntity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordinatorResponseDto> findById(@PathVariable Long id) {
        CoordinatorEntity coordinatorEntity = coordinatorService.findById(id);
        CoordinatorResponseDto coordinatorResponseDto = coordinatorDtoMapper.toResponse(coordinatorEntity);
        return ResponseEntity.ok().body(coordinatorResponseDto);
    }
}
