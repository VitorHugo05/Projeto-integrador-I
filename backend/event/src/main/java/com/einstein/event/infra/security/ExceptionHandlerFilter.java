package com.einstein.event.infra.security;

import com.einstein.event.controllers.exception.StandardError;
import com.einstein.event.services.exceptions.AccessDeniedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AccessDeniedException e) {
            response.setStatus(e.getStatus().value());
            response.setContentType("application/json");

            StandardError errorResponse = new StandardError(
                    System.currentTimeMillis(),
                    e.getStatus().value(),
                    "Access Denied",
                    e.getMessage(),
                    request.getRequestURI()
            );

            response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
        }
    }
}
