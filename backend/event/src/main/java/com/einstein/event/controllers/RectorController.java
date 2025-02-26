package com.einstein.event.controllers;

import com.einstein.event.dtos.request.RectorRequestDto;
import com.einstein.event.dtos.response.RectorResponseDto;
import com.einstein.event.entites.RectorEntity;
import com.einstein.event.mapper.RectorDtoMapper;
import com.einstein.event.services.AuthorizationService;
import com.einstein.event.services.RectorService;
import com.einstein.event.services.exceptions.DataAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/rector")
public class RectorController {

    @Autowired
    private RectorDtoMapper rectorDtoMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RectorService rectorService;
    @Autowired
    private AuthorizationService authorizationService;

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

    @PostMapping
    public ResponseEntity<Void> registerRector( @RequestBody RectorRequestDto rectorRequestDto) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rectorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody RectorRequestDto rectorRequestDto) {
        rectorService.update(rectorRequestDto, id);
        return ResponseEntity.noContent().build();
    }
}
