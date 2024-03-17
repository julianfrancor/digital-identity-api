package com.digitalidentityapi.brokerintermediary.controller;

import com.digitalidentityapi.brokerintermediary.dto.CitizenDto;
import com.digitalidentityapi.brokerintermediary.dto.DocumentDto;
import com.digitalidentityapi.brokerintermediary.dto.TransferDto;
import com.digitalidentityapi.brokerintermediary.service.IBrokerIntermediaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/citizens", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BrokerIntermediaryController {

    private final IBrokerIntermediaryService brokerIntermediaryService;

    @Autowired
    public BrokerIntermediaryController(IBrokerIntermediaryService brokerIntermediaryService) {
        this.brokerIntermediaryService = brokerIntermediaryService;
    }

    @PostMapping("/citizen")
    public ResponseEntity<String> createCitizen(@Valid @RequestBody CitizenDto citizenDto) {
        brokerIntermediaryService.handleCitizenOperations("CREATE", citizenDto);
        String messageResponse = String.format("Citizen with ID %s was sent to create.", citizenDto.getIdentification());
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PutMapping("/citizen")
    public ResponseEntity<String> updateCitizen(@Valid @RequestBody CitizenDto citizenDto) {
        brokerIntermediaryService.handleCitizenOperations("UPDATE", citizenDto);
        String messageResponse = String.format("Citizen with ID %s was sent to update.", citizenDto.getIdentification());
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/citizen")
    public ResponseEntity<String> deleteCitizen(@Valid @RequestBody CitizenDto citizenDto) {
        brokerIntermediaryService.handleCitizenOperations("DELETE", citizenDto);
        String messageResponse = String.format("Citizen with ID %s was sent to delete.", citizenDto.getIdentification());
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PostMapping("/document")
    public ResponseEntity<String> createDocument(@Valid @RequestBody DocumentDto documentDto) {
        brokerIntermediaryService.handleDocumentOperations("CREATE", documentDto);
        String messageResponse = String.format("Document with Title %s was sent to create.", documentDto.getTitle());
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PutMapping("/document")
    public ResponseEntity<String> updateDocument(@Valid @RequestBody DocumentDto documentDto) {
        brokerIntermediaryService.handleDocumentOperations("UPDATE", documentDto);
        String messageResponse = String.format("Document with Title %s was sent to update.", documentDto.getTitle());
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/document")
    public ResponseEntity<String> deleteDocument(@Valid @RequestBody DocumentDto documentDto) {
        brokerIntermediaryService.handleDocumentOperations("DELETE", documentDto);
        String messageResponse = String.format("Document with Title %s was sent to delete.", documentDto.getTitle());
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }

    @PostMapping("/transfers")
    public ResponseEntity<String> performTransfer(@Valid @RequestBody TransferDto transferDto) {
        brokerIntermediaryService.handleTransferOperations(transferDto);
        String messageResponse = String.format("Citizen with Email %s is being transferred.", transferDto.getCitizenEmail());
        return new ResponseEntity<>(messageResponse, HttpStatus.CREATED);
    }
}
