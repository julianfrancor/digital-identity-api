package com.digitalidentityapi.citizen.service;

import com.digitalidentityapi.citizen.dto.CitizenWithDocumentsTransferInfoDTO;
import com.digitalidentityapi.citizen.dto.TransferDto;
import com.digitalidentityapi.citizen.dto.TransferRequestDto;

import java.util.List;

public interface ITransferService {
    CitizenWithDocumentsTransferInfoDTO createTransfer(TransferRequestDto transferRequestDto);
    TransferDto updateTransfer(int id, TransferDto transferDto);
    void deleteTransfer(int id);
    TransferDto getTransferById(int id);
    List<TransferDto> getAllTransfers();
}
