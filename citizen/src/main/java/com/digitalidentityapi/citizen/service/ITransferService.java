package com.digitalidentityapi.citizen.service;

import com.digitalidentityapi.citizen.dto.TransferDto;

import java.util.List;

public interface ITransferService {
    TransferDto createTransfer(TransferDto transferDto);
    TransferDto updateTransfer(int id, TransferDto transferDto);
    void deleteTransfer(int id);
    TransferDto getTransferById(int id);
    List<TransferDto> getAllTransfers();
}
