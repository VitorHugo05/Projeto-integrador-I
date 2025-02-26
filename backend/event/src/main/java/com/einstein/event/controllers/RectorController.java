package com.einstein.event.controllers;

import com.einstein.event.dtos.request.RectorRequestDto;
import com.einstein.event.dtos.request.RectorUpdateRequestDto;
import com.einstein.event.dtos.response.RectorResponseDto;
import com.einstein.event.entites.RectorEntity;
import com.einstein.event.mapper.RectorDtoMapper;
import com.einstein.event.services.AuthorizationService;
import com.einstein.event.services.RectorService;
import com.einstein.event.services.exceptions.DataAlreadyExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/rector")
public class RectorController {

    private final RectorDtoMapper rectorDtoMapper;
    private final PasswordEncoder passwordEncoder;
    private final RectorService rectorService;
    private final AuthorizationService authorizationService;

    public RectorController(RectorDtoMapper rectorDtoMapper, PasswordEncoder passwordEncoder, RectorService rectorService, AuthorizationService authorizationService) {
        this.rectorDtoMapper = rectorDtoMapper;
        this.passwordEncoder = passwordEncoder;
        this.rectorService = rectorService;
        this.authorizationService = authorizationService;
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody RectorRequestDto rectorRequestDto) {
        if(authorizationService.validateEmail(rectorRequestDto.getEmail())) {
            throw new DataAlreadyExistException("Email already registered.");
        }
        if(authorizationService.validateCpf(rectorRequestDto.getCpf())) {
            throw new DataAlreadyExistException("CPF already registered");
        }

        RectorEntity rectorEntity = rectorDtoMapper.toEntity(rectorRequestDto);
        rectorEntity.setPassword(passwordEncoder.encode(rectorRequestDto.getPassword()));
        rectorEntity = rectorService.insert(rectorEntity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(rectorEntity.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RectorResponseDto> findById(@PathVariable Long id) {
        RectorEntity rectorEntity = rectorService.findById(id);
        RectorResponseDto rectorResponseDto = rectorDtoMapper.toResponse(rectorEntity);
        return ResponseEntity.ok().body(rectorResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<RectorResponseDto>> findAll() {
        List<RectorEntity> rectorEntityList = rectorService.findAll();
        List<RectorResponseDto> rectorResponseDtoList = rectorEntityList.stream()
                .map(rectorDtoMapper::toResponse)
                .toList();
        return ResponseEntity.ok().body(rectorResponseDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rectorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody RectorUpdateRequestDto rectorRequestDto) {
        rectorService.update(rectorRequestDto, id);
        return ResponseEntity.noContent().build();
    }
}
