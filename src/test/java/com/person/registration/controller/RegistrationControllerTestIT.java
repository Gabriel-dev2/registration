package com.person.registration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.person.registration.controller.dto.CustomApiResponse;
import com.person.registration.controller.dto.RegistrationDTO;
import com.person.registration.entities.Person;
import com.person.registration.enums.Messages;
import com.person.registration.repository.PersonRepository;

@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest
public class RegistrationControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }

    String id = "787.384.555-90";
    String name = "John Doe";
    Integer age = 33;
    String email = "John.Doe@gmail.com";
    String phone = "1197863526";
    String address = "Avenida dos Campos, 23, São Paulo";

    String id2 = "887.884.585-90";
    String name2 = "Lukas Podolski";
    Integer age2 = 45;
    String email2 = "lukas_podolki11@gmail.com";
    String phone2 = "11973283526";
    String address2 = "Avenida dos Campos, 23, São Paulo";

    RegistrationDTO registration = new RegistrationDTO(name, age, phone, email, address, id);

    @Test
    void testDeleteRegister() throws Exception{
        personRepository.save(new Person(id, name, age, phone, email, address, id));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/register/" + id))
        .andExpect(status().isNoContent());
    }

    @Test
    void testEditRegister() throws JsonProcessingException, Exception {
        Person person = personRepository.save(new Person(id, name, age, phone, email, address, id));
        registration.setAddress("Rua Tamoios, 7, Rio Doce, Olinda Pernambuco");
        
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/api/register/" + id)
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(registration))).andExpect(status().isOk()).andReturn();

        Optional<Person> personUpdated = personRepository.findById(id);
        CustomApiResponse apiResponse = mapper.readValue(result.getResponse().getContentAsString(), CustomApiResponse.class);
        
        assertEquals(Messages.UPDATED_REGISTER.getDescription(), apiResponse.getMessage());
        assertFalse(person.getAddress() == personUpdated.get().getAddress());
    }

    @Test
    void testFindById() throws Exception {
        Person person = personRepository.save(new Person(id, name, age, phone, email, address, id));
        personRepository.save(new Person(id2, name2, age2, phone2, email2, address2, id2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/register/" + id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(6))
        .andExpect(jsonPath("$.cpf").value(person.getCpf()))
        .andExpect(jsonPath("$.name").value(person.getName()))
        .andExpect(jsonPath("$.email").value(person.getEmail()))
        .andExpect(jsonPath("$.age").value(person.getAge()))
        .andExpect(jsonPath("$.phone").value(person.getPhone()))
        .andExpect(jsonPath("$.address").value(person.getAddress()));
    }

    @Test
    void testGetAllRegisters() throws Exception {
        Person person = personRepository.save(new Person(id, name, age, phone, email, address, id));
        Person person2 = personRepository.save(new Person(id2, name2, age2, phone2, email2, address2, id2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/register"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$.[0].cpf").value(person.getCpf()))
        .andExpect(jsonPath("$.[0].name").value(person.getName()))
        .andExpect(jsonPath("$.[0].email").value(person.getEmail()))
        .andExpect(jsonPath("$.[0].age").value(person.getAge()))
        .andExpect(jsonPath("$.[0].phone").value(person.getPhone()))
        .andExpect(jsonPath("$.[0].address").value(person.getAddress()))
        .andExpect(jsonPath("$.[1].cpf").value(person2.getCpf()))
        .andExpect(jsonPath("$.[1].name").value(person2.getName()))
        .andExpect(jsonPath("$.[1].email").value(person2.getEmail()))
        .andExpect(jsonPath("$.[1].age").value(person2.getAge()))
        .andExpect(jsonPath("$.[1].phone").value(person2.getPhone()))
        .andExpect(jsonPath("$.[1].address").value(person2.getAddress()));
    }

    @Test
    void testGetAllRegistersContaining() throws Exception {
        Person person = personRepository.save(new Person(id, name, age, phone, email, address, id));
        Person person2 = personRepository.save(new Person(id2, name2, age2, phone2, email2, address2, id2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/register/getByName/o"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$.[0].cpf").value(person.getCpf()))
        .andExpect(jsonPath("$.[0].name").value(person.getName()))
        .andExpect(jsonPath("$.[0].email").value(person.getEmail()))
        .andExpect(jsonPath("$.[0].age").value(person.getAge()))
        .andExpect(jsonPath("$.[0].phone").value(person.getPhone()))
        .andExpect(jsonPath("$.[0].address").value(person.getAddress()))
        .andExpect(jsonPath("$.[1].cpf").value(person2.getCpf()))
        .andExpect(jsonPath("$.[1].name").value(person2.getName()))
        .andExpect(jsonPath("$.[1].email").value(person2.getEmail()))
        .andExpect(jsonPath("$.[1].age").value(person2.getAge()))
        .andExpect(jsonPath("$.[1].phone").value(person2.getPhone()))
        .andExpect(jsonPath("$.[1].address").value(person2.getAddress()));
    }

    @Test
    void testRegisterPerson() throws JsonProcessingException, Exception {
       mockMvc.perform(MockMvcRequestBuilders.post("/api/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(registration)))
        .andExpect(status().isCreated());

        Optional<Person> person = personRepository.findById(id);

        assertTrue(person.isPresent());
        assertEquals(id, person.get().getId());
    }
}
