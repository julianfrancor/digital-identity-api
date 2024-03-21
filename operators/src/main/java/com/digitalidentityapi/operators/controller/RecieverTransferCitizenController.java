package com.digitalidentityapi.operators.controller;

import com.digitalidentityapi.operators.dto.RecieveCitizenDTO;
import com.digitalidentityapi.operators.service.RecieveCitizenServices;
import com.digitalidentityapi.operators.service.ValidateCitizenServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping(path = "/transfersidentidaddigital", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RecieverTransferCitizenController {
    @Autowired
    private ValidateCitizenServices validateCitizenServices;
    @Autowired
    private RecieveCitizenServices recieveCitizenServices;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping()
    public ResponseEntity<String> citizenRecieve(@Valid @RequestBody String message) throws Exception {
        System.out.println(message);
        RecieveCitizenDTO recieveCitizenDTO = objectMapper.readValue(message, RecieveCitizenDTO.class);
        System.out.println(recieveCitizenDTO.getId());
        if (Objects.equals(validateCitizenServices.validateCitizen(recieveCitizenDTO.getId().toString()), "Ciudadano ya registrado en Operador")) {
            return ResponseEntity.badRequest().body("Ciudadano ya registrado en operador - eliminelo de MinTic para proceder");
        } else {
            recieveCitizenServices.recieveCitizen(recieveCitizenDTO);
            return ResponseEntity.ok("Recibido el ciudadano con id: " + recieveCitizenDTO.getId() + " Para transferencia en Identidad Digital");
        }
    }

}

