package com.team03.challenge02.exception;

import com.team03.challenge02.student.exception.ErrorMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> constraintViolationException(DataIntegrityViolationException ex, HttpServletRequest st, BindingResult rs){
        log.error("Api error - ", ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(st, HttpStatus.UNPROCESSABLE_ENTITY,"Campos empity!", rs));
    }

    @ExceptionHandler(NameUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> uniqueViolationException(RuntimeException ex, HttpServletRequest st){
        log.error("Api error - ", ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(st, HttpStatus.CONFLICT,ex.getMessage()));
    }

    @ExceptionHandler(EntityIdNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityIdNotFoundException(RuntimeException ex, HttpServletRequest st){
        log.error("Api error - ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(st, HttpStatus.NOT_FOUND,ex.getMessage()));
    }

    @ExceptionHandler(EntityNameNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNameNotFoundException(RuntimeException ex, HttpServletRequest st){
        log.error("Api error - ", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(st, HttpStatus.NOT_FOUND,ex.getMessage()));
    }
}
