package com.person.registration.exceptions.custom;

public class RegistersNotFoundException extends Exception {
    
    public RegistersNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
