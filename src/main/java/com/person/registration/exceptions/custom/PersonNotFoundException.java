package com.person.registration.exceptions.custom;

public class PersonNotFoundException extends Exception {
    
    public PersonNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
