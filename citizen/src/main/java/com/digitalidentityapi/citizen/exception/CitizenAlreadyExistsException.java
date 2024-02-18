package com.digitalidentityapi.citizen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CitizenAlreadyExistsException extends RuntimeException {
    public CitizenAlreadyExistsException(String message){
        super(message);
    }
}
