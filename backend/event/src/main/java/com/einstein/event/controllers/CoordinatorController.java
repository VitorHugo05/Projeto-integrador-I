package com.einstein.event.controllers;

import com.einstein.event.dtos.request.CoordinatorRequestDto;
import com.einstein.event.dtos.request.CoordinatorUpdateRequestDto;
import com.einstein.event.dtos.response.CoordinatorResponseDto;
import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.mapper.CoordinatorDtoMapper;
import com.einstein.event.services.AuthorizationService;
import com.einstein.event.services.CoordinatorService;
import com.einstein.event.services.exceptions.DataAlreadyExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/coordinator")
public class CoordinatorController {

    private final CoordinatorService coordinatorService;
    private final CoordinatorDtoMapper coordinatorDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthorizationService authorizationService;

    public CoordinatorController(CoordinatorService coordinatorService, CoordinatorDtoMapper coordinatorDtoMapper, PasswordEncoder passwordEncoder, AuthorizationService authorizationService) {
        this.coordinatorService = coordinatorService;
        this.coordinatorDtoMapper = coordinatorDtoMapper;
        this.passwordEncoder = passwordEncoder;
        this.authorizationService = authorizationService;
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody CoordinatorRequestDto coordinatorRequestDto) {
        if(authorizationService.validateEmail(coordinatorRequestDto.getEmail())) {
            throw new IllegalArgumentException("Email já utilizado.");
        }
        if(authorizationService.validateCpf(coordinatorRequestDto.getCpf())) {
            throw new DataAlreadyExistException("CPF already registered");
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

    @GetMapping
    public ResponseEntity<List<CoordinatorResponseDto>> findAll() {
        List<CoordinatorEntity> coordinatorEntityList = coordinatorService.findAll();
        List<CoordinatorResponseDto> coordinatorResponseDtoList = coordinatorEntityList.stream()
                .map(coordinatorDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(coordinatorResponseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoordinatorResponseDto> findById(@PathVariable Long id) {
        CoordinatorEntity coordinatorEntity = coordinatorService.findById(id);
        CoordinatorResponseDto coordinatorResponseDto = coordinatorDtoMapper.toResponse(coordinatorEntity);
        return ResponseEntity.ok().body(coordinatorResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody CoordinatorUpdateRequestDto coordinatorRequestDto) {
        coordinatorService.update(id, coordinatorRequestDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        coordinatorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
