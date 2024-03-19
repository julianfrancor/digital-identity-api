package com.digitalidentityapi.citizen.service.impl;

import com.digitalidentityapi.citizen.dto.CitizenWithDocumentsTransferInfoDTO;
import com.digitalidentityapi.citizen.dto.TransferDto;
import com.digitalidentityapi.citizen.entity.Citizen;
import com.digitalidentityapi.citizen.entity.Transfer;
import com.digitalidentityapi.citizen.mapper.TransferMapper;
import com.digitalidentityapi.citizen.repository.CitizenRepository;
import com.digitalidentityapi.citizen.repository.TransferRepository;
import com.digitalidentityapi.citizen.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferServiceImpl implements ITransferService {

    @Autowired
    private final TransferRepository transferRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public TransferDto createTransfer(TransferDto transferDto) {
        CitizenWithDocumentsTransferInfoDTO citizenWithDocumentsTransferInfoDTO = new CitizenWithDocumentsTransferInfoDTO();
        Citizen citizen = getCitizenByEmail(transferDto);

        citizenWithDocumentsTransferInfoDTO.setId(citizen.getIdentification());
        citizenWithDocumentsTransferInfoDTO.setName(buildFullName(citizen));
        citizenWithDocumentsTransferInfoDTO.setAddress(citizen.getAddress());
        citizenWithDocumentsTransferInfoDTO.setEmail(citizen.getEmail());
        // TODO: mirar como se hace esto del callback
        citizenWithDocumentsTransferInfoDTO.setCallbackUrl("example.com");

        List<DocumentInfoDTO> documents = new ArrayList<>();
        CitizenWithDocumentsTransferInfoDTO.DocumentInfoDTO ccDocument = new CitizenWithDocumentsTransferInfoDTO.DocumentInfoDTO();
        ccDocument.setDocumentTitle("CC");
        ccDocument.setUrlDocument("example.com");

        CitizenWithDocumentsTransferInfoDTO.DocumentInfoDTO licenseDocument = new CitizenWithDocumentsTransferInfoDTO.DocumentInfoDTO();
        licenseDocument.setDocumentTitle("LICENCIA");
        licenseDocument.setUrlDocument("example2.com");

        documents.add(ccDocument);
        documents.add(licenseDocument);

        citizenWithDocumentsTransferInfoDTO.setFiles(documents);


        String citizenEmail = transferDto.getCitizenEmail();



        Transfer transfer = TransferMapper.toEntity(transferDto, new Transfer());
        transfer.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        transfer.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        transfer = transferRepository.save(transfer);
        return TransferMapper.toDto(transfer, citizenEmail);
    }

    private Citizen getCitizenByEmail(TransferDto transferDto) {
        return citizenRepository.findByEmail(transferDto.getCitizenEmail()).orElseThrow(() ->
                new IllegalStateException("Citizen with email " + transferDto.getCitizenEmail() + " does not exist"));
    }

    private static String buildFullName(Citizen citizen) {
        StringBuilder fullNameBuilder = new StringBuilder();

        fullNameBuilder.append(citizen.getFirstName());
        if (citizen.getSecondName() != null && !citizen.getSecondName().isEmpty()) {
            fullNameBuilder.append(" ").append(citizen.getSecondName());
        }
        fullNameBuilder.append(" ").append(citizen.getLastName());
        if (citizen.getSecondLastName() != null && !citizen.getSecondLastName().isEmpty()) {
            fullNameBuilder.append(" ").append(citizen.getSecondLastName());
        }
        return fullNameBuilder.toString();
    }

    @Override
    public TransferDto updateTransfer(int id, TransferDto transferDto) {
        String citizenEmail = transferDto.getCitizenEmail();
        Transfer existingTransfer = transferRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transfer with ID " + id + " not found"));

        existingTransfer.setCitizenId(Integer.parseInt(transferDto.getCitizenId()));
        existingTransfer.setOperatorId(Integer.parseInt(transferDto.getOperatorId()));
        existingTransfer.setTransferDate(transferDto.getTransferDate());
        existingTransfer.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));

        transferRepository.save(existingTransfer);
        return TransferMapper.toDto(existingTransfer, citizenEmail);
    }

    @Override
    public void deleteTransfer(int id) {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transfer with ID " + id + " not found"));
        transferRepository.delete(transfer);
    }

    @Override
    public TransferDto getTransferById(int id) {
        Transfer transfer = transferRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transfer with ID " + id + " not found"));
        Citizen citizen = citizenRepository.findById(Integer.valueOf(String.valueOf(transfer.getCitizenId()))).orElseThrow(() ->
                new IllegalStateException("Citizen with ID " + transfer.getCitizenId() + " does not exist"));
        return TransferMapper.toDto(transfer, citizen.getEmail());
    }

    @Override
    public List<TransferDto> getAllTransfers() {
        return transferRepository.findAll().stream()
                .map(transfer -> {
                    return TransferMapper.toDto(transfer, (citizenRepository.findById(Integer.valueOf(String.valueOf(transfer.getCitizenId()))).orElseThrow(() ->
                            new IllegalStateException("Citizen with ID " + transfer.getCitizenId() + " does not exist")).getEmail()));
                })
                .collect(Collectors.toList());
    }
}
