package com.person.registration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.person.registration.entities.Person;

@SpringBootTest
public class PersonRepositoryTestIT {
    
    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    void setUp() throws Exception {
        personRepository.deleteAll();
    }

    @Test
    void findPersonByName() {
        Person person = new Person("998.997.883-08", "Lucas Carlos da Silva", 33, "87990742617", "lucas78@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "998.997.883-08");
        Person person2 = new Person("555.956.835-08", "Gabriel Lucas Lins SIlva", 26, "87990742617", "gabriel57@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "555.956.835-08");
        Person person3 = new Person("563.542.234-08", "Carlos Alberto de Nóbrega", 50, "87990742617", "carlos_alberto@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "563.542.234-08");
        
        personRepository.save(person);
        personRepository.save(person2);
        personRepository.save(person3);

        List<Person> personReceived = personRepository.findPersonByName("Lucas");
        
        assertEquals(2, personReceived.size());
    }

    @Test
    void registerPerson() {
        Person person = new Person("998.997.883-08", "Lucas Carlos da Silva", 33, "87990742617", "lucas78@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "998.997.883-08");
        
        Person personReceived = personRepository.save(person);

        assertEquals(person.getName(), personReceived.getName());
        assertEquals(person.getCpf(), personReceived.getCpf());
    }

    @Test
    void findPersonById() {
        Person person = new Person("998.997.883-08", "Lucas Carlos da Silva", 33, "87990742617", "lucas78@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "998.997.883-08");
        Person person2 = new Person("555.956.835-08", "Gabriel Lucas Lins SIlva", 26, "87990742617", "gabriel57@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "555.956.835-08");
        Person person3 = new Person("563.542.234-08", "Carlos Alberto de Nóbrega", 50, "87990742617", "carlos_alberto@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "563.542.234-08");
        
        personRepository.save(person);
        personRepository.save(person2);
        personRepository.save(person3);

        Optional<Person> personReceived = personRepository.findById("555.956.835-08");

        assertEquals(person2.getName(), personReceived.get().getName());
        assertEquals(person2.getId(), personReceived.get().getId());
    }

    @Test
    void findAllRegisters() {
        Person person = new Person("998.997.883-08", "Lucas Carlos da Silva", 33, "87990742617", "lucas78@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "998.997.883-08");
        Person person2 = new Person("555.956.835-08", "Gabriel Lucas Lins SIlva", 26, "87990742617", "gabriel57@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "555.956.835-08");
        Person person3 = new Person("563.542.234-08", "Carlos Alberto de Nóbrega", 50, "87990742617", "carlos_alberto@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "563.542.234-08");
        
        personRepository.save(person);
        personRepository.save(person2);
        personRepository.save(person3);

        List<Person> personReceived = personRepository.findAll();

        assertEquals(3, personReceived.size());
    }

    @Test
    void existsById() {
        Person person = new Person("998.997.883-08", "Lucas Carlos da Silva", 33, "87990742617", "lucas78@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "998.997.883-08");

        personRepository.save(person);

        boolean exists = personRepository.existsById(person.getId());

        assertTrue(exists);
    }

    @Test
    void deleteRegister() {
        Person person = new Person("998.997.883-08", "Lucas Carlos da Silva", 33, "87990742617", "lucas78@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "998.997.883-08");
        Person person2 = new Person("555.956.835-08", "Gabriel Lucas Lins SIlva", 26, "87990742617", "gabriel57@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "555.956.835-08");
        Person person3 = new Person("563.542.234-08", "Carlos Alberto de Nóbrega", 50, "87990742617", "carlos_alberto@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "563.542.234-08");
        
        personRepository.save(person);
        personRepository.save(person2);
        personRepository.save(person3);

        personRepository.deleteById(person3.getId());

        boolean exists = personRepository.existsById(person3.getId());

        List<Person> personReceived = personRepository.findAll();

        assertFalse(exists);
        assertEquals(2, personReceived.size());
    }

    @Test
    void updateRegister() {
        Person person = new Person("998.997.883-08", "Lucas Carlos da Silva", 33, "87990742617", "lucas78@outlook.com", "Rua Carochinha, 34, Recife, Pernambuco", "998.997.883-08");
        
        personRepository.save(person);

        Person personCopy = new Person("998.997.883-08", "Lucas Carlos da Silva", 33, "87990742617", "carlos_silva67@gmail.com", "Rua Carochinha, 34, Recife, Pernambuco", "998.997.883-08");

        Person personReceived = personRepository.save(personCopy);
        
        assertEquals(person.getCpf(), personReceived.getCpf());
        assertFalse(personReceived.getEmail() == person.getEmail());
    }
}
