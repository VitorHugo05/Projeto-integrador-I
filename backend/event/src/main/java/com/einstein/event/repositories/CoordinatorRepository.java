package com.einstein.event.repositories;

import com.einstein.event.entites.CoordinatorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinatorRepository extends JpaRepository<CoordinatorEntity, Long> {
    Optional<CoordinatorEntity> findByEmail(String email);
}
