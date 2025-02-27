package com.einstein.event.services;

import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.entites.RectorEntity;
import com.einstein.event.entites.StudentEntity;
import com.einstein.event.repositories.CoordinatorRepository;
import com.einstein.event.repositories.RectorRepository;
import com.einstein.event.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    private final CoordinatorRepository coordinatorRepository;
    private final RectorRepository rectorRepository;
    private final StudentRepository studentRepository;

    public AuthorizationService(CoordinatorRepository coordinatorRepository, RectorRepository rectorRepository, StudentRepository studentRepository) {
        this.coordinatorRepository = coordinatorRepository;
        this.rectorRepository = rectorRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CoordinatorEntity> coordinatorEntity = coordinatorRepository.findByEmail(username);
        if(coordinatorEntity.isPresent()) {
            return coordinatorEntity.get();
        }

        Optional<StudentEntity> studentEntity = studentRepository.findByEmail(username);
        if(studentEntity.isPresent()) {
            return studentEntity.get();
        }

        Optional<RectorEntity> rectorEntity = rectorRepository.findByEmail(username);
        if(rectorEntity.isPresent()) {
            return rectorEntity.get();
        }

        throw new UsernameNotFoundException("User not found");
    }

    public boolean validateEmail(String email) {
        boolean validCoordinator = coordinatorRepository.findByEmail(email).isPresent();
        boolean validStudent = studentRepository.findByEmail(email).isPresent();
        boolean validRector = rectorRepository.findByEmail(email).isPresent();

        return validCoordinator || validStudent || validRector;
    }

    public boolean validateCpf(String cpf) {
        boolean validCoordinator = coordinatorRepository.findByCpf(cpf).isPresent();
        boolean validRector = rectorRepository.findByCpf(cpf).isPresent();

        return validCoordinator || validRector;
    }
}
