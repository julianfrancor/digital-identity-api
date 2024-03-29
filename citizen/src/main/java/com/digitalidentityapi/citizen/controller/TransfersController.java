package com.digitalidentityapi.citizen.controller;

import com.digitalidentityapi.citizen.dto.CitizenWithDocumentsTransferInfoDTO;
import com.digitalidentityapi.citizen.dto.TransferDto;
import com.digitalidentityapi.citizen.dto.TransferRequestDto;
import com.digitalidentityapi.citizen.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizen-service/transfers")
public class TransfersController {

    private final ITransferService transfersService;

    @Autowired
    public TransfersController(ITransferService transfersService) {
        this.transfersService = transfersService;
    }

    @GetMapping
    public ResponseEntity<List<TransferDto>> getAllTransfers() {
        List<TransferDto> transfers = transfersService.getAllTransfers();
        return ResponseEntity.ok(transfers);
    }

    @PostMapping
    public ResponseEntity<CitizenWithDocumentsTransferInfoDTO> createTransfer(@RequestBody TransferRequestDto transferRequestDto) {
        CitizenWithDocumentsTransferInfoDTO newTransfer = transfersService.createTransfer(transferRequestDto);
        return new ResponseEntity<>(newTransfer, HttpStatus.CREATED);
    }

}
