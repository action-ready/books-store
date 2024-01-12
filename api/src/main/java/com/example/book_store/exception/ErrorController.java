package com.example.book_store.exception;


import com.example.book_store.payload.response.ErrorResponse;
import com.example.book_store.payload.response.WebResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception exception) {
        String message = "Unexpected error";
        String detailMessage = exception.getLocalizedMessage();
        int code = HttpStatus.BAD_REQUEST.value();
        ErrorResponse errorResponse = new ErrorResponse(code, message, detailMessage, exception);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(), "Validation error",
                "One or more fields failed validation", errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<WebResponse<String>> apiException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getStatusCode())
                .body(WebResponse.<String>builder().errors(exception.getReason()).build());
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> numberFormatExceptionHandler(NumberFormatException exception) {
        String message = "Invalid id format. Please provide a valid numeric id.";
        String detailMessage = exception.getLocalizedMessage();
        int code = HttpStatus.BAD_REQUEST.value();
        ErrorResponse errorResponse = new ErrorResponse(code, message, detailMessage, exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}