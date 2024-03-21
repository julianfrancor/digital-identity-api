package com.digitalidentityapi.operators.service;

import com.digitalidentityapi.operators.dto.DtoRegisterCitizen;
import com.digitalidentityapi.operators.dto.RecieveCitizenDTO;
import com.digitalidentityapi.operators.entity.CitizenRecieveOperator;
import org.springframework.http.ResponseEntity;

public interface RecieveCitizenServices {
    void recieveCitizen(RecieveCitizenDTO CitizenRecieveOperator) throws Exception;
}
