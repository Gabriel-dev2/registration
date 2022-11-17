package com.person.registration.unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.person.registration.controller.dto.RegistrationDTO;
import com.person.registration.controller.mapper.PersonMapper;
import com.person.registration.entities.Person;
import com.person.registration.exceptions.custom.PersonAlreadyExistsException;
import com.person.registration.exceptions.custom.PersonNotFoundException;
import com.person.registration.exceptions.custom.RegistersNotFoundException;
import com.person.registration.repository.PersonRepository;
import com.person.registration.service.RegistrationService;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    RegistrationService registrationService;

    PersonMapper personMapper = new PersonMapper();

    @Test
    @DisplayName("Test deleteRegister by Id Success")
    void testDeleteRegister() throws PersonNotFoundException {
        String id = "787.384.555-90";

        given(personRepository.existsById(id)).willReturn(true);

        registrationService.deleteRegister(id);

        verify(personRepository, times(1)).existsById(id);
        verify(personRepository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Test deleteRegister by Id that doesn't exist")
    void testDeleteRegisterFail() throws PersonNotFoundException {
        String id = "787.384.555-90";

        when(personRepository.existsById(id)).thenReturn(false);

        PersonNotFoundException thrown = assertThrows(PersonNotFoundException.class, () -> {
            registrationService.deleteRegister(id);
        });

        verify(personRepository, times(1)).existsById(id);
        verify(personRepository, times(0)).deleteById(id);
        assertEquals("Person with id 787.384.555-90 not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Test findById Success")
    void testFindById() throws Exception {
        String id = "787.384.555-90";
        String name = "John Doe";
        Integer age = 33;
        String email = "John.Doe@gmail.com";
        String phone = "1197863526";
        String address = "Avenida dos Campos, 23, São Paulo";

        Person person = new Person(id, name, age, phone, email, address, id);

        when(personRepository.findById(id)).thenReturn(Optional.of(person));

        RegistrationDTO registration = registrationService.findById(id);

        verify(personRepository, times(1)).findById(id);
        assertEquals(registration.getCpf(), person.getCpf());
    }

    @Test
    @DisplayName("Test findById Fail with Exception Not Found")
    void testFindByIdNotFound() throws PersonNotFoundException {
        String id = "787.384.555-90";

        when(personRepository.findById(id)).thenReturn(Optional.empty());

        PersonNotFoundException thrown = assertThrows(PersonNotFoundException.class, () -> {
            registrationService.findById(id);
        });

        verify(personRepository, times(1)).findById(id);
        assertEquals("Person with id 787.384.555-90 not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Test findByName")
    void testFindByName() throws Exception {
        String id = "787.384.555-90";
        String name = "John Doe";
        Integer age = 33;
        String email = "John.Doe@gmail.com";
        String phone = "1197863526";
        String address = "Avenida dos Campos, 23, São Paulo";

        String id2 = "667.384.555-90";
        String name2 = "Michel Bastos";
        Integer age2 = 23;
        String email2 = "michelgmail.com";
        String phone2 = "99873267324";
        String address2 = "Avenida dos Campos, 23, São Paulo";

        String id3 = "999.384.555-90";
        String name3 = "Luna Michele";
        Integer age3 = 35;
        String email3 = "luna@gmail.com";
        String phone3 = "9978327484";
        String address3 = "Avenida dos Campos, 23, São Paulo";

        List<Person> people = new ArrayList<>();
        people.add(new Person(id, name, age, phone, email, address, id));
        people.add(new Person(id2, name2, age2, phone2, email2, address2, id2));
        people.add(new Person(id3, name3, age3, phone3, email3, address3, id3));

        when(this.personRepository.findPersonByName(any(String.class))).thenReturn(people.subList(0, 2));
        
        List<RegistrationDTO> peopleReceived = registrationService.findByNameContaining("Miche");
        
        verify(this.personRepository, times(1)).findPersonByName("Miche");
        assertEquals(2, peopleReceived.size());
    }

    @Test
    @DisplayName("Test findByName Not Found")
    void testFindByNameFail() throws Exception {

        List<Person> people = new ArrayList<>();

        when(this.personRepository.findPersonByName(any(String.class))).thenReturn(people);
        
        RegistersNotFoundException thrown = assertThrows(RegistersNotFoundException.class, () -> {
            registrationService.findByNameContaining("Miche");
        });
        
        verify(this.personRepository, times(1)).findPersonByName("Miche");
        assertEquals(0, people.size());
        assertEquals("Registers not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Test getAllRegisters Success")
    void testGetAllRegisters() throws RegistersNotFoundException {
        String id = "787.384.555-90";
        String name = "John Doe";
        Integer age = 33;
        String email = "John.Doe@gmail.com";
        String phone = "1197863526";
        String address = "Avenida dos Campos, 23, São Paulo";

        String id2 = "667.384.555-90";
        String name2 = "Michel Bastos";
        Integer age2 = 23;
        String email2 = "michelgmail.com";
        String phone2 = "99873267324";
        String address2 = "Avenida dos Campos, 23, São Paulo";

        String id3 = "999.384.555-90";
        String name3 = "Luna Michele";
        Integer age3 = 35;
        String email3 = "luna@gmail.com";
        String phone3 = "9978327484";
        String address3 = "Avenida dos Campos, 23, São Paulo";
        
        List<Person> people = new ArrayList<>();
        people.add(new Person(id, name, age, phone, email, address, id));
        people.add(new Person(id2, name2, age2, phone2, email2, address2, id2));
        people.add(new Person(id3, name3, age3, phone3, email3, address3, id3));
        
        when(personRepository.findAll()).thenReturn(people);

        List<RegistrationDTO> registrersReturned = registrationService.getAllRegisters();

        verify(personRepository, times(1)).findAll();
        assertEquals(registrersReturned.get(2).getCpf(), people.get(2).getCpf());
        assertEquals(registrersReturned.get(1).getCpf(), people.get(1).getCpf());
        assertEquals(registrersReturned.get(0).getCpf(), people.get(0).getCpf());
    }

    @Test
    @DisplayName("Test getAllRegisters Fail with RegistersNotFoundException")
    void testGetAllRegistersFail() throws RegistersNotFoundException {

        List<Person> people = new ArrayList<>();
        
        when(personRepository.findAll()).thenReturn(people);

        RegistersNotFoundException thrown = assertThrows(RegistersNotFoundException.class, () -> {
            registrationService.getAllRegisters();
        });

        verify(personRepository, times(1)).findAll();
        assertEquals("Registers not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Test registerPerson Success")
    void testRegisterPerson() throws PersonAlreadyExistsException {
        String id = "667.384.555-90";
        String name = "Michel Bastos";
        Integer age = 23;
        String email = "michelgmail.com";
        String phone = "99873267324";
        String address = "Avenida dos Campos, 23, São Paulo";

        RegistrationDTO register = new RegistrationDTO(name, age, phone, email, address, id);
        Person person = personMapper.toPersonEntity(register);

        when(personRepository.existsById(id)).thenReturn(false);
        when(personRepository.save(any(Person.class))).thenReturn(person);

        registrationService.registerPerson(register);

        verify(personRepository, times(1)).existsById(id);
        verify(personRepository, times(1)).save(any(Person.class));
        assertEquals(register.getCpf(), person.getCpf());
    }

    @Test
    @DisplayName("Test registerPerson Fail with PersonAlreadyExistsException")
    void testRegisterPersonThatAlreadyExists() throws PersonAlreadyExistsException {
        String id = "667.384.555-90";
        String name = "Michel Bastos";
        Integer age = 23;
        String email = "michelgmail.com";
        String phone = "99873267324";
        String address = "Avenida dos Campos, 23, São Paulo";

        RegistrationDTO register = new RegistrationDTO(name, age, phone, email, address, id);

        when(personRepository.existsById(id)).thenReturn(true);

        PersonAlreadyExistsException thrown = assertThrows(PersonAlreadyExistsException.class, () -> {
            registrationService.registerPerson(register);
        });

        verify(personRepository, times(1)).existsById(id);
        verify(personRepository, times(0)).save(any(Person.class));
        assertEquals("Person already exists", thrown.getMessage());
    }

    @Test
    @DisplayName("Test updateRegister Success")
    void testUpdateRegister() throws PersonNotFoundException {
        String id = "667.384.555-90";
        String name = "Michel Bastos";
        Integer age = 23;
        String email = "michelgmail.com";
        String phone = "99873267324";
        String address = "Avenida dos Campos, 23, São Paulo";

        RegistrationDTO register = new RegistrationDTO(name, age, phone, email, address, id);
        Person person = personMapper.toPersonEntity(register);
        person.setEmail("michel.bas55@hotmail.com");

        when(personRepository.existsById(id)).thenReturn(true);
        when(personRepository.save(any(Person.class))).thenReturn(person);

        registrationService.updateRegister(id, register);

        verify(personRepository, times(1)).existsById(id);
        verify(personRepository, times(1)).save(any(Person.class));
        assertNotEquals(register.getEmail(), person.getEmail());
    }

    @Test
    @DisplayName("Test updateRegister Failt with PersonNotFoundException")
    void testUpdateRegisterThatDontExist() throws PersonNotFoundException {
        String id = "667.384.555-90";
        String name = "Michel Bastos";
        Integer age = 23;
        String email = "michelgmail.com";
        String phone = "99873267324";
        String address = "Avenida dos Campos, 23, São Paulo";

        RegistrationDTO register = new RegistrationDTO(name, age, phone, email, address, id);

        when(personRepository.existsById(id)).thenReturn(false);

        PersonNotFoundException thrown = assertThrows(PersonNotFoundException.class, () -> {
            registrationService.updateRegister(id, register);
        });

        verify(personRepository, times(1)).existsById(id);
        assertNotEquals("Person already exists", thrown.getMessage());
    }
}
