package com.person.registration.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("person")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    
    @Id
    private String id;

    private String name;
    private Integer age;
    private String phone;
    private String email;
    private String address;
    private String cpf;
}
