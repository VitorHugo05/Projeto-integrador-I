package com.einstein.event.infra.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.entites.RectorEntity;
import com.einstein.event.entites.StudentEntity;
import com.einstein.event.repositories.StudentRepository;
import com.einstein.event.repositories.RectorRepository;
import com.einstein.event.repositories.CoordinatorRepository;
import com.einstein.event.services.exceptions.AccessDeniedException;
import com.einstein.event.services.exceptions.ObjectNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RectorRepository rectorRepository;

    @Autowired
    private CoordinatorRepository coordinatorRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (request.getRequestURI().startsWith("/api/auth/login")) {
                filterChain.doFilter(request, response);
                return;
            }

            var token = this.recoveryToken(request);
            if (token != null) {
                var subject = tokenService.verifyToken(token);
                if (subject != null && !subject.isEmpty()) {
                    UserDetails user = findUserById(Long.valueOf(subject));

                    var role = tokenService.getRoleFromToken(token);
                    var auth = new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority(role)));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid or expired token");
        } catch (ObjectNotFoundException e) {
            throw new AccessDeniedException("JWT User not found or removed", HttpStatus.UNAUTHORIZED);
        }
    }


    private UserDetails findUserById(Long userId) {
        StudentEntity student = studentRepository.findById(userId).orElse(null);
        if (student != null) return student;

        RectorEntity rector = rectorRepository.findById(userId).orElse(null);
        if (rector != null) return rector;

        CoordinatorEntity coordinator = coordinatorRepository.findById(userId).orElse(null);
        if (coordinator != null) return coordinator;

        throw new ObjectNotFoundException("JWT User not found or removed");
    }

    private String recoveryToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        return (authHeader != null && authHeader.startsWith("Bearer ")) ? authHeader.replace("Bearer ", "") : null;
    }
}
