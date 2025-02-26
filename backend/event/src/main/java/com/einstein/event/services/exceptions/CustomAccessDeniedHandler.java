package com.einstein.event.services.exceptions;

import com.einstein.event.controllers.exception.StandardError;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        StandardError errorResponse = new StandardError(
                System.currentTimeMillis(),
                HttpServletResponse.SC_FORBIDDEN,
                "Forbidden",
                "Access denied. You do not have permission to access this resource.",
                request.getRequestURI()
        );

        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}
