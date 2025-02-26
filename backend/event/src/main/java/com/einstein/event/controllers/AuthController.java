package com.einstein.event.controllers;

import com.einstein.event.dtos.request.LoginRequestDto;
import com.einstein.event.dtos.request.StudentRequestDto;
import com.einstein.event.dtos.response.AuthResponseDto;
import com.einstein.event.entites.StudentEntity;
import com.einstein.event.entites.UserEntity;
import com.einstein.event.infra.security.TokenService;
import com.einstein.event.mapper.StudentDtoMapper;
import com.einstein.event.services.StudentService;
import com.einstein.event.services.exceptions.IncorrectCredentialsException;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentDtoMapper studentDtoMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto authRequestDto) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword());
            var auth = authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateToken((UserEntity) auth.getPrincipal());
            return ResponseEntity.ok().body(new AuthResponseDto(token));
        } catch (UsernameNotFoundException e) {
            throw new ObjectNotFoundException("User not found");
        } catch (BadCredentialsException e) {
            throw new IncorrectCredentialsException("Invalid email or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody StudentRequestDto studentRequestDto){
        StudentEntity studentEntity = studentDtoMapper.toEntity(studentRequestDto);
        studentEntity.setPassword(passwordEncoder.encode(studentRequestDto.getPassword()));
        StudentEntity studentResponse = studentService.insert(studentEntity);
        var token = tokenService.generateToken(studentResponse);
        return ResponseEntity.ok().body(new AuthResponseDto(token));
    }
}
