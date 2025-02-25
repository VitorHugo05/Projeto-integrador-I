package com.einstein.event.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.einstein.event.entites.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${security.secret}")
    private String secret;

    public String generateToken(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("event")
                    .withSubject(user.getId().toString())
                    .withClaim("role", "ROLE_" + user.getRole().name())
                    .withExpiresAt(generateExpirationTime())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public String verifyToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("event")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException e){
            return "";
        }
    }

    private Instant generateExpirationTime(){
        return LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.of("-03:00"));
    }
}
