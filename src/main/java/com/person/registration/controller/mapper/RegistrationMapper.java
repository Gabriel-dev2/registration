package com.person.registration.controller.mapper;

import org.springframework.stereotype.Component;

import com.person.registration.controller.dto.RegistrationDTO;
import com.person.registration.entities.Person;

@Component
public class RegistrationMapper {
    public RegistrationDTO toRegistrationDTO(Person registrationDTO) {
        return new RegistrationDTO(registrationDTO.getName(), registrationDTO.getAge(), registrationDTO.getPhone(), registrationDTO.getEmail(), registrationDTO.getAddress(), registrationDTO.getCpf());
    }
}
