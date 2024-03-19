package com.digitalidentityapi.citizen.service.impl;

import com.digitalidentityapi.citizen.dto.CitizenDto;
import com.digitalidentityapi.citizen.entity.Citizen;
import com.digitalidentityapi.citizen.exception.CitizenAlreadyExistsException;
import com.digitalidentityapi.citizen.mapper.CitizenMapper;
import com.digitalidentityapi.citizen.producer.RabbitPublishMessage;
import com.digitalidentityapi.citizen.repository.CitizenRepository;
import com.digitalidentityapi.citizen.service.ICitizenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.digitalidentityapi.citizen.constants.Constants.NOTIFICATION_QUEUE;
import static com.digitalidentityapi.citizen.constants.Constants.REGISTER_CITIZEN_QUEUE;

@Service
@AllArgsConstructor
public class CitizenServiceImpl implements ICitizenService {

    private CitizenRepository citizenRepository;

    @Autowired
    private final RabbitPublishMessage rabbitPublishMessage;

    @Override
    public void createCitizen(CitizenDto citizenDto) {
        Optional<Citizen> existingCitizen = citizenRepository.findByEmail(citizenDto.getEmail());
        if (existingCitizen.isPresent()) {
            String messageCitizenAlreadyCreated = String.format("Citizen with email %s is already created.", existingCitizen.get().getEmail());

            String message = String.format("{\n" +
                    "    \"email\": \"%s\",\n" +
                    "    \"message\": \"%s\"\n" +
                    "}", existingCitizen.get().getEmail(), messageCitizenAlreadyCreated);

            rabbitPublishMessage.sendMessageToQueue(NOTIFICATION_QUEUE, message);
            return;
        }

        Citizen citizen = CitizenMapper.toEntity(citizenDto);
        citizen.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        citizen.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        citizenRepository.save(citizen);

        rabbitPublishMessage.sendMessageToQueue(REGISTER_CITIZEN_QUEUE, getRegisterCitizenMessageString(citizen));
        rabbitPublishMessage.sendMessageToQueue(NOTIFICATION_QUEUE, getNotificationMessageString(citizen));
    }

    private static String getRegisterCitizenMessageString(Citizen citizen) {
        String fullName = buildFullName(citizen);

        Long identificationInt;
        try {
            identificationInt = citizen.getIdentification();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Identification must be a numeric value.");
        }

        return String.format("{\n" +
                "    \"id\": \"%d\",\n" +
                "    \"name\": \"%s\",\n" +
                "    \"address\": \"%s\",\n" +
                "    \"email\": \"%s\"\n" +
                "}", identificationInt, fullName, citizen.getAddress(), citizen.getEmail());
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

    private static String getNotificationMessageString(Citizen existingCitizen) {
        String messageCitizenSuccessfullyCreated = String.format("Citizen with email %s was successfully created.", existingCitizen.getEmail());
        return String.format("{\n" +
                "    \"email\": \"%s\",\n" +
                "    \"message\": \"%s\"\n" +
                "}", existingCitizen.getEmail(), messageCitizenSuccessfullyCreated);
    }


    @Override
    public void updateCitizen(String email, CitizenDto citizenDto) {
        Citizen citizen = citizenRepository.findByEmail(email).orElseThrow(() ->
                new IllegalStateException("Citizen with email " + email + " does not exist"));
        BeanUtils.copyProperties(citizenDto, citizen, "id");
        citizen.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        citizenRepository.save(citizen);
    }

    @Override
    public CitizenDto getCitizenByEmail(String email) {
        Citizen citizen = citizenRepository.findByEmail(email).orElseThrow(() ->
                new IllegalStateException("Citizen with Email " + email + " does not exist"));
        CitizenDto citizenDto = new CitizenDto();
        BeanUtils.copyProperties(citizen, citizenDto);
        return citizenDto;
    }

    @Override
    public void deleteCitizen(String email) {
        Citizen citizen = citizenRepository.findByEmail(email).orElseThrow(() ->
                new IllegalStateException("Citizen with Email " + email + " does not exist"));
        citizenRepository.delete(citizen);
    }

    @Override
    public List<CitizenDto> getAllCitizens() {
        return citizenRepository.findAll().stream()
                .map(CitizenMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CitizenDto> searchCitizens(String criteria) {
        // This method should implement the logic to search for citizens based on the criteria.
        // For simplicity, it's returning an empty list.
        return List.of();
    }

    @Override
    public void restoreCitizen(String id) {
        // This method should implement the logic to restore a soft-deleted citizen.
    }

    @Override
    public void registerCitizenForPremiumService(String citizenId, String serviceId) {
        // This method should implement the logic to register a citizen for a given service.
    }

    @Override
    public void unregisterCitizenFromPremiumService(String citizenId, String serviceId) {
        // This method should implement the logic to unregister a citizen from a given service.
    }

    @Override
    public void transferCitizen(String citizenId, String targetOperatorId) {
        // This method should implement the logic to transfer a citizen to a different operator.
        // TODO: revisar si debo de encola para el servicio de transferencia o si esta parte se encarga de hacer algo en la DB cuando alguien fue transferido
    }

    @Override
    public boolean verifyCitizenIdentity(String citizenId) {
        // This method should implement the logic to verify a citizen's identity.
        //TODO: Encolar en Broker para Servicio de Autenticación o revisar bien que hacer con este metodo
        return false;
    }
}
