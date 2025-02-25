package com.einstein.event.services;

import com.einstein.event.entites.RectorEntity;
import com.einstein.event.repositories.RectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RectorService {
    @Autowired
    private RectorRepository rectorRepository;

    public List<RectorEntity> findAll() {
        return rectorRepository.findAll();
    }

    public RectorEntity insert(RectorEntity rectorEntity) {
        if(rectorRepository.findByCpf(rectorEntity.getCpf()).isEmpty()) {
            return rectorRepository.save(rectorEntity);
        } else {
            throw new RuntimeException("Erro ao inserir rector");
        }
    }

    public RectorEntity findById(Long id) {
        return rectorRepository.findById(id).orElseThrow(() -> new RuntimeException("Erro ao inserir rector"));
    }
}
