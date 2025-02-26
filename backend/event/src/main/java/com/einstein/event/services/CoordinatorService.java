package com.einstein.event.services;

import com.einstein.event.dtos.request.CoordinatorRequestDto;
import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.mapper.CoordinatorDtoMapper;
import com.einstein.event.repositories.CoordinatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoordinatorService {
    @Autowired
    private CoordinatorRepository coordinatorRepository;

    @Autowired
    private CoordinatorDtoMapper coordinatorDtoMapper;

    public CoordinatorEntity insert(CoordinatorEntity coordinatorEntity) {
        return coordinatorRepository.save(coordinatorEntity);
    }

    public CoordinatorEntity findById(Long id) {
        return coordinatorRepository.findById(id).orElseThrow(() -> new RuntimeException("Coordinator not found"));
    }
}
