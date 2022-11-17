package com.person.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.person.registration.controller.dto.CustomApiResponse;
import com.person.registration.enums.Messages;
import com.person.registration.exceptions.custom.PersonAlreadyExistsException;
import com.person.registration.exceptions.custom.PersonNotFoundException;
import com.person.registration.exceptions.custom.RegistersNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<CustomApiResponse> handlePersonNotFoundException(PersonNotFoundException e) {
        CustomApiResponse response = new CustomApiResponse();
        response.setMessage(e.getMessage());
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomApiResponse> handleIllegalArgumentException() {
        CustomApiResponse response = new CustomApiResponse();
        response.setMessage(Messages.ILLEGAL_ARGUMENT.getDescription());
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<CustomApiResponse> handlePersonAlreadyExistsException(PersonAlreadyExistsException e) {
        CustomApiResponse response = new CustomApiResponse();
        response.setMessage(e.getMessage());
        response.setStatusCode(HttpStatus.CONFLICT.value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(RegistersNotFoundException.class)
    public ResponseEntity<CustomApiResponse> handleRegistersNotFoundException(RegistersNotFoundException e) {
        CustomApiResponse response = new CustomApiResponse();
        response.setMessage(e.getMessage());
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
