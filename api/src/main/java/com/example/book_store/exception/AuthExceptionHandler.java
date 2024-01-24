package com.example.book_store.exception;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Unauthorized");
        errorResponse.setDetailMessage(authException.getMessage());
        errorResponse.setCode(HttpServletResponse.SC_UNAUTHORIZED);
        errorResponse.setException(authException.getClass().getName());

        addErrorResponseToBodyResponse(errorResponse, response, HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Access Denied");
        errorResponse.setDetailMessage(accessDeniedException.getMessage());
        errorResponse.setCode(HttpServletResponse.SC_FORBIDDEN);
        errorResponse.setException(accessDeniedException.getClass().getName());

        addErrorResponseToBodyResponse(errorResponse, response, HttpServletResponse.SC_FORBIDDEN);
    }


    public void addErrorResponseToBodyResponse(ErrorResponse errorResponse, HttpServletResponse response, int responseStatus) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(responseStatus);
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class ErrorResponse {
        private Integer code;
        private String message;
        private String detailMessage;
        private Object exception;
    }
}
