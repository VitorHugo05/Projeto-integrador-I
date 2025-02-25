package com.einstein.event.infra.security;

import com.einstein.event.entites.CoordinatorEntity;
import com.einstein.event.entites.RectorEntity;
import com.einstein.event.entites.StudentEntity;
import com.einstein.event.repositories.StudentRepository;
import com.einstein.event.repositories.RectorRepository;
import com.einstein.event.repositories.CoordinatorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/api/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoveryToken(request);
        if (token != null) {
            var subject = tokenService.verifyToken(token);
            if (subject != null && !subject.isEmpty()) {
                UserDetails user = findUserById(Long.valueOf(subject));

                if (user == null) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not found");
                    return;
                }

                var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }

    private UserDetails findUserById(Long userId) {
        StudentEntity student = studentRepository.findById(userId).orElse(null);
        if (student != null) return student;

        RectorEntity rector = rectorRepository.findById(userId).orElse(null);
        if (rector != null) return rector;

        return coordinatorRepository.findById(userId).orElse(null);
    }

    private String recoveryToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
