package com.digitalidentityapi.citizen.service.impl;

import com.digitalidentityapi.citizen.dto.TransfersDto;
import com.digitalidentityapi.citizen.entity.Citizen;
import com.digitalidentityapi.citizen.entity.Transfer;
import com.digitalidentityapi.citizen.mapper.TransfersMapper;
import com.digitalidentityapi.citizen.repository.CitizenRepository;
import com.digitalidentityapi.citizen.repository.TransfersRepository;
import com.digitalidentityapi.citizen.service.ITransfersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransfersServiceImpl implements ITransfersService {

    @Autowired
    private final TransfersRepository transfersRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    public TransfersServiceImpl(TransfersRepository transfersRepository) {
        this.transfersRepository = transfersRepository;
    }

    @Override
    public TransfersDto createTransfer(TransfersDto transferDto) {
        String citizenEmail = transferDto.getCitizenEmail();
        Transfer transfer = TransfersMapper.toEntity(transferDto, new Transfer());
        transfer.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        transfer.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        transfer = transfersRepository.save(transfer);
        return TransfersMapper.toDto(transfer, citizenEmail);
    }

    @Override
    public TransfersDto updateTransfer(int id, TransfersDto transferDto) {
        String citizenEmail = transferDto.getCitizenEmail();
        Transfer existingTransfer = transfersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transfer with ID " + id + " not found"));

        existingTransfer.setCitizenId(Integer.parseInt(transferDto.getCitizenId()));
        existingTransfer.setOperatorId(Integer.parseInt(transferDto.getOperatorId()));
        existingTransfer.setTransferDate(transferDto.getTransferDate());
        existingTransfer.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));

        transfersRepository.save(existingTransfer);
        return TransfersMapper.toDto(existingTransfer, citizenEmail);
    }

    @Override
    public void deleteTransfer(int id) {
        Transfer transfer = transfersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transfer with ID " + id + " not found"));
        transfersRepository.delete(transfer);
    }

    @Override
    public TransfersDto getTransferById(int id) {
        Transfer transfer = transfersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transfer with ID " + id + " not found"));
        Citizen citizen = citizenRepository.findById(Integer.valueOf(String.valueOf(transfer.getCitizenId()))).orElseThrow(() ->
                new IllegalStateException("Citizen with ID " + transfer.getCitizenId() + " does not exist"));
        return TransfersMapper.toDto(transfer, citizen.getEmail());
    }

    @Override
    public List<TransfersDto> getAllTransfers() {
        return transfersRepository.findAll().stream()
                .map(transfer -> {
                    return TransfersMapper.toDto(transfer, (citizenRepository.findById(Integer.valueOf(String.valueOf(transfer.getCitizenId()))).orElseThrow(() ->
                            new IllegalStateException("Citizen with ID " + transfer.getCitizenId() + " does not exist")).getEmail()));
                })
                .collect(Collectors.toList());
    }
}
