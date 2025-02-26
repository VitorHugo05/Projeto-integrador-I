package com.einstein.event.services;

import com.einstein.event.dtos.request.RectorRequestDto;
import com.einstein.event.entites.RectorEntity;
import com.einstein.event.mapper.RectorDtoMapper;
import com.einstein.event.repositories.RectorRepository;
import com.einstein.event.services.exceptions.DataAlreadyExistException;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RectorService {
    @Autowired
    private RectorRepository rectorRepository;

    @Autowired
    private RectorDtoMapper rectorDtoMapper;

    public List<RectorEntity> findAll() {
        return rectorRepository.findAll();
    }

    public RectorEntity insert(RectorEntity rectorEntity) {
        if(rectorRepository.findByCpf(rectorEntity.getCpf()).isEmpty()) {
            return rectorRepository.save(rectorEntity);
        } else {
            throw new DataAlreadyExistException("Is there already a registration with this CPF");
        }
    }

    public RectorEntity findById(Long id) {
        return rectorRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Rector not found"));
    }

    public void delete(Long id) {
        findById(id);
        rectorRepository.deleteById(id);
    }

    public void update(RectorRequestDto rectorRequestDto, Long id) {
        findById(id);
        RectorEntity rectorEntity = rectorDtoMapper.toEntity(rectorRequestDto);
        rectorEntity.setId(id);
        rectorRepository.save(rectorEntity);
    }
}
