package com.apidecrypto.controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.decrypto.exception.NestedEntityNotFoundException;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

	Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class); 

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
		    LOGGER.error(errorMessage);
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
    
    @ExceptionHandler(NestedEntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNestedEntityNotFoundException(NestedEntityNotFoundException ex) {
	    LOGGER.error(ex.getMessage());
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nested Entity with ID was not found: " +  ex.getMessage());
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleRecursoNoEncontradoException(EntityNotFoundException ex) {
	    LOGGER.error(ex.getMessage());
    	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity with ID was not found: " + ex.getMessage());
    }
    
    @ExceptionHandler(EntityExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleExistExceptionException(EntityExistsException ex) {
	    LOGGER.error(ex.getMessage());
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entity already exist: " + ex.getMessage());
    }
    
    
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<String> handleIntegrityConstraintViolation(SQLIntegrityConstraintViolationException ex) {
	    LOGGER.error(ex.getMessage());
    	return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception ex) {
	    LOGGER.error(ex.getMessage());
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
    

}