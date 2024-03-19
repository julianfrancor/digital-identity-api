package com.digitalidentityapi.operators.controller;

import com.digitalidentityapi.operators.service.GetOperatorsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/getoperators", produces = {MediaType.APPLICATION_JSON_VALUE})
public class getOperatorsController {
    @Autowired
    private GetOperatorsServices getOperatorsServices;

    @GetMapping
    public String getOperators() {
        return getOperatorsServices.getOperators();
    }
}


