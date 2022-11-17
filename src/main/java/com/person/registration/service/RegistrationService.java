package com.person.registration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.person.registration.controller.dto.RegistrationDTO;
import com.person.registration.controller.mapper.PersonMapper;
import com.person.registration.controller.mapper.RegistrationMapper;
import com.person.registration.entities.Person;
import com.person.registration.enums.Messages;
import com.person.registration.exceptions.custom.PersonAlreadyExistsException;
import com.person.registration.exceptions.custom.PersonNotFoundException;
import com.person.registration.exceptions.custom.RegistersNotFoundException;
import com.person.registration.repository.PersonRepository;

@Service
public class RegistrationService {

    @Autowired
    PersonRepository personRepository;

    PersonMapper personMapper = new PersonMapper();

    RegistrationMapper registrationMapper = new RegistrationMapper();
    
    public void registerPerson(RegistrationDTO registrationDTO) throws PersonAlreadyExistsException{
        try {
            if (!this.personRepository.existsById(registrationDTO.getCpf())) {
                Person person = this.personMapper.toPersonEntity(registrationDTO);
                this.personRepository.save(person);
            } else {
                throw new PersonAlreadyExistsException(Messages.PERSON_ALREADY_EXISTS.getDescription());
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<RegistrationDTO> getAllRegisters() throws RegistersNotFoundException {
        List<RegistrationDTO> registrationDTOs = new ArrayList<>();
        List<Person> person = new ArrayList<>();
        try {
            person = this.personRepository.findAll();
        
            if (person.size() == 0) {
                throw new RegistersNotFoundException(Messages.REGISTERS_NOT_FOUND.getDescription());
            }

            person.forEach( p -> registrationDTOs.add(this.registrationMapper.toRegistrationDTO(p)));
        } catch (Exception e) {
            throw e;
        }

        return registrationDTOs;
    }

    public RegistrationDTO findById(String id) throws Exception {
        RegistrationDTO registrationDTO = null;

        try {
            Optional<Person> person = this.personRepository.findById(id); 

            if (person.isPresent()) {
                registrationDTO = this.registrationMapper.toRegistrationDTO(person.get());
            } else {
                throw new PersonNotFoundException(String.format(Messages.PERSON_NOT_FOUND.getDescription(), id));
            }

        } catch (Exception e) {
            throw e;
        }

        return registrationDTO;
    }

    public List<RegistrationDTO> findByNameContaining(String name) throws RegistersNotFoundException {
        List<RegistrationDTO> registrationDTOList = new ArrayList<>();

        try {
            List<Person> person = this.personRepository.findPersonByName(name);

            if (person.size() == 0) {
                throw new RegistersNotFoundException(Messages.REGISTERS_NOT_FOUND.getDescription());
            }

            person.forEach( p -> registrationDTOList.add(this.registrationMapper.toRegistrationDTO(p)));
        } catch (Exception e) {
            throw e;
        }

        return registrationDTOList;
    }

    public void deleteRegister(String id) throws PersonNotFoundException {

        try {
            if (this.personRepository.existsById(id)) {

                this.personRepository.deleteById(id);

            } else {
                throw new PersonNotFoundException(String.format(Messages.PERSON_NOT_FOUND.getDescription(), id));
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    public void updateRegister(String id, RegistrationDTO registrationDTO) throws PersonNotFoundException{

        try {
            if (this.personRepository.existsById(id)) {
                Person person = this.personMapper.toPersonEntity(registrationDTO);
                this.personRepository.save(person);
            } else {
                throw new PersonNotFoundException(String.format(Messages.PERSON_NOT_FOUND.getDescription(), id));
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}
