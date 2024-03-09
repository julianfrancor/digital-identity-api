package com.digitalidentityapi.citizen.service.impl;

import com.digitalidentityapi.citizen.dto.CitizenDto;
import com.digitalidentityapi.citizen.entity.Citizen;
import com.digitalidentityapi.citizen.exception.CitizenAlreadyExistsException;
import com.digitalidentityapi.citizen.mapper.CitizenMapper;
import com.digitalidentityapi.citizen.repository.CitizenRepository;
import com.digitalidentityapi.citizen.service.ICitizenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CitizenServiceImpl implements ICitizenService {

    private CitizenRepository citizenRepository;

    @Override
    public void createCitizen(CitizenDto citizenDto) {
        Optional<Citizen> existingCitizen = citizenRepository.findByEmail(citizenDto.getEmail());
        if (existingCitizen.isPresent()) {
            throw new CitizenAlreadyExistsException("Citizen already registered with email: " + citizenDto.getEmail() + "already exists.");
        }

        Citizen citizen = CitizenMapper.mapToCitizen(citizenDto, new Citizen());
        citizen.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        citizen.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        citizenRepository.save(citizen);
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
                .map(CitizenMapper::mapToCitizenDto).collect(Collectors.toList());
    }

    @Override
    public List<CitizenDto> searchCitizens(String criteria) {
        // This method should implement the logic to search for citizens based on the criteria.
        // For simplicity, it's returning an empty list.
        return List.of();
    }

    @Override
    public void restoreCitizen(UUID id) {
        // This method should implement the logic to restore a soft-deleted citizen.
    }

    @Override
    public void registerCitizenForPremiumService(UUID citizenId, UUID serviceId) {
        // This method should implement the logic to register a citizen for a given service.
    }

    @Override
    public void unregisterCitizenFromPremiumService(UUID citizenId, UUID serviceId) {
        // This method should implement the logic to unregister a citizen from a given service.
    }

    @Override
    public void transferCitizen(UUID citizenId, UUID targetOperatorId) {
        // This method should implement the logic to transfer a citizen to a different operator.
    }

    @Override
    public boolean verifyCitizenIdentity(UUID citizenId) {
        // This method should implement the logic to verify a citizen's identity.
        return false;
    }
}
