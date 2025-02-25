package com.einstein.event.repositories;

import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.entites.RectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RectorRepository extends JpaRepository<RectorEntity, Long> {
    List<RectorEntity> findByCpf(String cpf);
    Optional<RectorEntity> findByEmail(String email);
}
