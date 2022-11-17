package com.person.registration.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomApiResponse {
    private Integer statusCode;
    private String message;
}
