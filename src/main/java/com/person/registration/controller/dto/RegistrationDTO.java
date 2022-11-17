package com.person.registration.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {
    private String name;
    private Integer age;
    private String phone;
    private String email;
    private String address;
    private String cpf;
}
