package com.digitalidentityapi.citizen.service.impl;

import com.digitalidentityapi.citizen.Message.TransferCitizenMessage;
import com.digitalidentityapi.citizen.dto.CitizenWithDocumentsTransferInfoDTO;
import com.digitalidentityapi.citizen.dto.CitizenWithDocumentsTransferInfoDTO.DocumentTransferInfo;
import com.digitalidentityapi.citizen.dto.DocumentDto;
import com.digitalidentityapi.citizen.dto.TransferDto;
import com.digitalidentityapi.citizen.dto.TransferRequestDto;
import com.digitalidentityapi.citizen.entity.Citizen;
import com.digitalidentityapi.citizen.entity.Document;
import com.digitalidentityapi.citizen.entity.Transfer;
import com.digitalidentityapi.citizen.mapper.DocumentMapper;
import com.digitalidentityapi.citizen.mapper.TransferMapper;
import com.digitalidentityapi.citizen.producer.RabbitPublishMessage;
import com.digitalidentityapi.citizen.repository.CitizenRepository;
import com.digitalidentityapi.citizen.repository.DocumentRepository;
import com.digitalidentityapi.citizen.repository.TransferRepository;
import com.digitalidentityapi.citizen.service.ITransferService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.digitalidentityapi.citizen.constants.Constants.TRANSFER_CITIZEN_QUEUE;

@Service
public class TransferServiceImpl implements ITransferService {

    @Autowired
    private final TransferRepository transferRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private final RabbitPublishMessage rabbitPublishMessage;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public TransferServiceImpl(TransferRepository transferRepository, RabbitPublishMessage rabbitPublishMessage) {
        this.transferRepository = transferRepository;
        this.rabbitPublishMessage = rabbitPublishMessage;
    }

    @Override
    public CitizenWithDocumentsTransferInfoDTO createTransfer(TransferRequestDto transferRequestDto) {
        CitizenWithDocumentsTransferInfoDTO citizenWithDocumentsTransferInfoDTO = new CitizenWithDocumentsTransferInfoDTO();
        Citizen citizen = getCitizenByEmail(transferRequestDto.getCitizenEmail());

        citizenWithDocumentsTransferInfoDTO.setId(citizen.getIdentification());
        citizenWithDocumentsTransferInfoDTO.setName(citizen.getFullName());
        citizenWithDocumentsTransferInfoDTO.setAddress(citizen.getAddress());
        citizenWithDocumentsTransferInfoDTO.setEmail(citizen.getEmail());
        // TODO: mirar como se hace esto del callback
        citizenWithDocumentsTransferInfoDTO.setCallbackUrl("example.com");
        List<DocumentDto> allDocuments = getAllDocumentsByCitizenEmail(citizen.getEmail());
        citizenWithDocumentsTransferInfoDTO.setFiles(convertToDocumentTransferInfoList(allDocuments));

        TransferDto transferDto = getTransferDto(citizen, transferRequestDto.getDestinationOperatorId());
        registerTransferInDatabase(transferDto);

        TransferCitizenMessage transferCitizenMessage = new TransferCitizenMessage(transferRequestDto.getDestinationOperatorId(), citizenWithDocumentsTransferInfoDTO);
        String transferMessage = getCitizenTransferMessageString(transferCitizenMessage);
        System.out.println(transferMessage);
        rabbitPublishMessage.sendMessageToQueue(TRANSFER_CITIZEN_QUEUE, transferMessage);
        return citizenWithDocumentsTransferInfoDTO;
    }

    private static TransferDto getTransferDto(Citizen citizen, int operatorId) {
        TransferDto transferDto = new TransferDto();
        transferDto.setCitizenId(citizen.getId());
        transferDto.setCitizenEmail(citizen.getEmail());
        transferDto.setOperatorId(String.valueOf(operatorId));
        transferDto.setTransferDate(new Date());
        transferDto.setCreatedAt(new Date());
        transferDto.setUpdatedAt(new Date());
        return transferDto;
    }

    private String getCitizenTransferMessageString(TransferCitizenMessage transferCitizenMessage) {
        String message = "";
        try {
            message = objectMapper.writeValueAsString(transferCitizenMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return message;
    }

    private void registerTransferInDatabase(TransferDto transferDto) {
        Transfer transfer = TransferMapper.toEntity(transferDto, new Transfer());
        transfer.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        transfer.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        transferRepository.save(transfer);
    }

    private Citizen getCitizenByEmail(String citizenEmail) {
        return citizenRepository.findByEmail(citizenEmail).orElseThrow(() ->
                new IllegalStateException("Citizen with email " + citizenEmail + " does not exist"));
    }

    private List<DocumentDto> getAllDocumentsByCitizenEmail(String email) {
        List<Document> documents = documentRepository.findAllByCitizenEmail(email);
        return documents.stream()
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    }

    private List<DocumentTransferInfo> convertToDocumentTransferInfoList(List<DocumentDto> allDocuments) {
        return allDocuments.stream()
                .map(document -> new DocumentTransferInfo(document.getTitle(), document.getUrl()))
                .collect(Collectors.toList());
    }

    @Override
    public TransferDto updateTransfer(int id, TransferDto transferDto) {
        String citizenEmail = transferDto.getCitizenEmail();
        Transfer existingTransfer = transferRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transfer with ID " + id + " not found"));

        existingTransfer.setCitizenId(transferDto.getCitizenId());
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
        Citizen citizen = citizenRepository.findById((long) Integer.parseInt(String.valueOf(transfer.getCitizenId()))).orElseThrow(() ->
                new IllegalStateException("Citizen with ID " + transfer.getCitizenId() + " does not exist"));
        return TransferMapper.toDto(transfer, citizen.getEmail());
    }

    @Override
    public List<TransferDto> getAllTransfers() {
        return transferRepository.findAll().stream()
                .map(transfer -> {
                    return TransferMapper.toDto(transfer, (citizenRepository.findById((long) Integer.parseInt(String.valueOf(transfer.getCitizenId()))).orElseThrow(() ->
                            new IllegalStateException("Citizen with ID " + transfer.getCitizenId() + " does not exist")).getEmail()));
                })
                .collect(Collectors.toList());
    }
}
