package com.person.registration.enums;

public enum Messages {
    PERSON_NOT_FOUND("Person with id %s not found"),
    ILLEGAL_ARGUMENT("Invalid Argument"),
    PERSON_ALREADY_EXISTS("Person already exists"),
    UPDATED_REGISTER("Register updated successfully"),
    REGISTERS_NOT_FOUND("Registers not found");

    private String description;

    Messages(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
