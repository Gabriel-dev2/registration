package com.person.registration.exceptions.custom;

public class PersonAlreadyExistsException extends Exception {
    
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}
