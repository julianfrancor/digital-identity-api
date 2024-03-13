package com.digitalidentityapi.operators.controller;

import com.digitalidentityapi.operators.service.ValidateCitizenServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/validateCitizen", produces = {MediaType.APPLICATION_JSON_VALUE})
public class validateCitizenController {

    @Autowired
    private ValidateCitizenServices validateCitizenServices;

    @GetMapping()
    public String getOperators(@RequestParam String id) {
        return validateCitizenServices.validateCitizen(id);
    }

}

