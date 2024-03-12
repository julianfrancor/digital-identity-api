package com.digitalidentityapi.citizen.service;

import com.digitalidentityapi.citizen.dto.TransfersDto;
import java.util.List;

public interface ITransfersService {
    TransfersDto createTransfer(TransfersDto transferDto);
    TransfersDto updateTransfer(int id, TransfersDto transferDto);
    void deleteTransfer(int id);
    TransfersDto getTransferById(int id);
    List<TransfersDto> getAllTransfers();
}
