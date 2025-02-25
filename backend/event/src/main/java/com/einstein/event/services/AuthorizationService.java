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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private CoordinatorRepository coordinatorRepository;

    @Autowired
    private RectorRepository rectorRepository;

    @Autowired
    private StudentRepository studentRepository;

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
}
