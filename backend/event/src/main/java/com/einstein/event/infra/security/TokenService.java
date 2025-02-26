package com.einstein.event.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.algorithms.Algorithm;
import com.einstein.event.entites.UserEntity;
import com.einstein.event.services.exceptions.AccessDeniedException;
import com.einstein.event.services.exceptions.InternalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
            throw new InternalException("Error while generating token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var decodedJWT = JWT.require(algorithm)
                    .withIssuer("event")
                    .build()
                    .verify(token);

            return decodedJWT.getSubject();
        } catch (JWTVerificationException e) {
            throw new AccessDeniedException("Invalid or expired token", HttpStatus.UNAUTHORIZED);
        }
    }

    public String getRoleFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var decodedJWT = JWT.require(algorithm)
                    .withIssuer("event")
                    .build()
                    .verify(token);

            return decodedJWT.getClaim("role").asString();
        } catch (JWTVerificationException e) {
            throw new AccessDeniedException("Invalid or expired token", HttpStatus.UNAUTHORIZED);
        }
    }

    private Instant generateExpirationTime() {
        return LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.of("-03:00"));
    }
}
