package com.einstein.event.controllers;

import com.einstein.event.dtos.request.LoginRequestDto;
import com.einstein.event.dtos.request.StudentRequestDto;
import com.einstein.event.dtos.response.AuthResponseDto;
import com.einstein.event.entites.StudentEntity;
import com.einstein.event.entites.UserEntity;
import com.einstein.event.infra.security.TokenService;
import com.einstein.event.services.AuthorizationService;
import com.einstein.event.services.StudentService;
import com.einstein.event.services.exceptions.DataAlreadyExistException;
import com.einstein.event.services.exceptions.IncorrectCredentialsException;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final StudentService studentService;
    private final AuthorizationService authorizationService;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, StudentService studentService, AuthorizationService authorizationService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.studentService = studentService;
        this.authorizationService = authorizationService;
    }

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
        if(authorizationService.validateEmail(studentRequestDto.getEmail())){
            throw new DataAlreadyExistException("Email is already in use");
        }
        StudentEntity studentResponse = studentService.insert(studentRequestDto);
        var token = tokenService.generateToken(studentResponse);
        return ResponseEntity.ok().body(new AuthResponseDto(token));
    }
}
