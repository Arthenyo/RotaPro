package com.arthenyo.rotapro_backend.controllers.handlers;

import com.arthenyo.rotapro_backend.customErro.CustomException;
import com.arthenyo.rotapro_backend.services.exception.DateBaseException;
import com.arthenyo.rotapro_backend.services.exception.ObjectNotFound;
import com.arthenyo.rotapro_backend.services.exception.ResponseStatusException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(ObjectNotFound.class)
    public ResponseEntity<CustomException> objectNotFound(ObjectNotFound e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomException err = new CustomException(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CustomException> dataBase(ResponseStatusException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomException err = new CustomException(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
