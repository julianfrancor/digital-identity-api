package com.digitalidentityapi.brokerintermediary.service;

import com.digitalidentityapi.brokerintermediary.dto.CitizenDto;
import com.digitalidentityapi.brokerintermediary.dto.DocumentDto;
import com.digitalidentityapi.brokerintermediary.dto.SignDocumentDto;
import com.digitalidentityapi.brokerintermediary.dto.TransferRequestDto;

public interface IBrokerIntermediaryService {

    void handleCitizenOperations(String operation, CitizenDto citizenDto);
    void handleDocumentOperations(String operation, DocumentDto documentDto);
    void handleTransferOperations(TransferRequestDto transferRequestDto);
    void handleSignDocumentOperations(SignDocumentDto signDocumentDto);
}

