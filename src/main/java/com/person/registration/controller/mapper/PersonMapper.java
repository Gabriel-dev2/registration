package com.person.registration.controller.mapper;

import org.springframework.stereotype.Component;

import com.person.registration.controller.dto.RegistrationDTO;
import com.person.registration.entities.Person;

@Component
public class PersonMapper {

    public Person toPersonEntity(RegistrationDTO registrationDTO) {
        return new Person(registrationDTO.getCpf(), registrationDTO.getName(), registrationDTO.getAge(), registrationDTO.getPhone(), registrationDTO.getEmail(), registrationDTO.getAddress(), registrationDTO.getCpf());
    }
}
