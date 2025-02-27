package com.einstein.event.services;

import com.einstein.event.dtos.request.CoordinatorUpdateRequestDto;
import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.mapper.CoordinatorDtoMapper;
import com.einstein.event.repositories.CoordinatorRepository;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordinatorService {
    private final CoordinatorRepository coordinatorRepository;
    private final CoordinatorDtoMapper coordinatorDtoMapper;

    public CoordinatorService(CoordinatorRepository coordinatorRepository, CoordinatorDtoMapper coordinatorDtoMapper) {
        this.coordinatorRepository = coordinatorRepository;
        this.coordinatorDtoMapper = coordinatorDtoMapper;
    }

    public CoordinatorEntity insert(CoordinatorEntity coordinatorEntity) {
        return coordinatorRepository.save(coordinatorEntity);
    }

    public CoordinatorEntity findById(Long id) {
        return coordinatorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Coordinator not found"));
    }

    public CoordinatorEntity findByCpf(String coordinatorCpf) {
        return coordinatorRepository.findByCpf(coordinatorCpf).orElseThrow(() -> new ObjectNotFoundException("Coordinator not found"));
    }

    public void update(Long id, CoordinatorUpdateRequestDto coordinatorUpdateRequestDto) {
        findById(id);
        CoordinatorEntity coordinatorEntity = coordinatorDtoMapper.toEntity(coordinatorUpdateRequestDto);
        coordinatorEntity.setId(id);
        coordinatorRepository.save(coordinatorEntity);
    }

    public void deleteById(Long id) {
        findById(id);
        coordinatorRepository.deleteById(id);
    }

    public List<CoordinatorEntity> findAll() {
        return coordinatorRepository.findAll();
    }
}
