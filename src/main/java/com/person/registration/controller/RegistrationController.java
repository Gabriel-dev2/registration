package com.person.registration.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.person.registration.controller.dto.CustomApiResponse;
import com.person.registration.controller.dto.RegistrationDTO;
import com.person.registration.enums.Messages;
import com.person.registration.exceptions.custom.PersonAlreadyExistsException;
import com.person.registration.exceptions.custom.PersonNotFoundException;
import com.person.registration.exceptions.custom.RegistersNotFoundException;
import com.person.registration.service.RegistrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "Registration Controller", tags = "Registration", description = "Registraion's Controller")
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;
    
    @PostMapping(path = "/register")
    @ApiOperation(value = "Register a person", nickname = "register")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void registerPerson(@RequestBody RegistrationDTO registrationDTO) throws PersonAlreadyExistsException {

        registrationService.registerPerson(registrationDTO);
    }

    @GetMapping(path = "/register")
    @ApiOperation(value = "List all people registered", nickname = "register", response = RegistrationDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity<List<RegistrationDTO>> getAllRegisters() throws RegistersNotFoundException {
        List<RegistrationDTO> response = new ArrayList<RegistrationDTO>();

        response = registrationService.getAllRegisters();

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(path = "/register/{id}")
    @ApiOperation(value = "Shows a register filtered by id", nickname = "register", response = RegistrationDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity<RegistrationDTO> findById(@PathVariable String id) throws Exception {
        RegistrationDTO registrationDTO = new RegistrationDTO();

        registrationDTO = this.registrationService.findById(id);

        return ResponseEntity.ok(registrationDTO);
    }

    @GetMapping(path = "/register/getByName/{name}")
    @ApiOperation(value = "Show registers filtered by name or character contained", nickname = "getByName", response = RegistrationDTO.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "")
    })
    public ResponseEntity<List<RegistrationDTO>> getAllRegistersContaining(@PathVariable String name) throws RegistersNotFoundException {
        List<RegistrationDTO> response = new ArrayList<RegistrationDTO>();

        response = registrationService.findByNameContaining(name);

        return ResponseEntity.ok().body(response);
    } 

    @DeleteMapping(path = "/register/{id}")
    @ApiOperation(value = "Delete a register by id", nickname = "register")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRegister(@PathVariable String id) throws PersonNotFoundException {

        this.registrationService.deleteRegister(id);
    }

    @PutMapping(path = "/register/{id}")
    @ApiOperation(value = "Delete a register by id", nickname = "register", response = CustomApiResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Register updated successfully")
    })
    public ResponseEntity<CustomApiResponse> editRegister(@PathVariable String id, @RequestBody RegistrationDTO registrationDTO) throws PersonNotFoundException {
        CustomApiResponse response = new CustomApiResponse();

        this.registrationService.updateRegister(id, registrationDTO);
        response.setMessage(Messages.UPDATED_REGISTER.getDescription());
        response.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}