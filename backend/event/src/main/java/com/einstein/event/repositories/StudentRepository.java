package com.einstein.event.repositories;

import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.entites.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByEmail(String email);

    Optional<StudentEntity> findByRa(String ra);
}
