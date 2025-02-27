package com.einstein.event.services;

import com.einstein.event.dtos.request.RectorUpdateRequestDto;
import com.einstein.event.entites.RectorEntity;
import com.einstein.event.mapper.RectorDtoMapper;
import com.einstein.event.repositories.RectorRepository;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RectorService {

    private final RectorRepository rectorRepository;
    private final RectorDtoMapper rectorDtoMapper;

    public RectorService(RectorRepository rectorRepository, RectorDtoMapper rectorDtoMapper) {
        this.rectorRepository = rectorRepository;
        this.rectorDtoMapper = rectorDtoMapper;
    }

    public List<RectorEntity> findAll() {
        return rectorRepository.findAll();
    }

    public RectorEntity insert(RectorEntity rectorEntity) {
        return rectorRepository.save(rectorEntity);
    }

    public RectorEntity findById(Long id) {
        return rectorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Rector not found"));
    }

    public void delete(Long id) {
        findById(id);
        rectorRepository.deleteById(id);
    }

    public void update(RectorUpdateRequestDto rectorRequestDto, Long id) {
        findById(id);
        RectorEntity rectorEntity = rectorDtoMapper.toEntity(rectorRequestDto);
        rectorEntity.setId(id);
        rectorRepository.save(rectorEntity);
    }
}
