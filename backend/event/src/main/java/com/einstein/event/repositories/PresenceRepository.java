package com.einstein.event.repositories;

import com.einstein.event.entites.StudentPresenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PresenceRepository extends JpaRepository<StudentPresenceEntity, Long> {
    Boolean existsByStudentRaAndEventId(String ra, Long eventId);
    List<StudentPresenceEntity> findAllByEventId(Long eventId);
    List<StudentPresenceEntity> findAllByStudentRa(String ra);
}
