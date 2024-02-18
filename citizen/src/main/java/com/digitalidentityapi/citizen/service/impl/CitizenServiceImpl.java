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

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CitizenServiceImpl implements ICitizenService {

    private CitizenRepository citizenRepository;

    @Override
    public void createCitizen(CitizenDto citizenDto) {
        Optional<Citizen> existingCitizen = citizenRepository.findById(citizenDto.getId());
        if (existingCitizen.isPresent()) {
            throw new CitizenAlreadyExistsException("Citizen already registered with email: " + citizenDto.getEmail() + "already exists.");
        }

        Citizen citizen = CitizenMapper.mapToCitizen(citizenDto, new Citizen());
        citizenRepository.save(citizen);
    }

    @Override
    public void updateCitizen(UUID id, CitizenDto citizenDto) {
        Citizen citizen = citizenRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Citizen with ID " + id + " does not exist"));
        BeanUtils.copyProperties(citizenDto, citizen, "id"); // Do not copy the ID field
        citizenRepository.save(citizen);
    }

    @Override
    public CitizenDto getCitizenById(UUID id) {
        Citizen citizen = citizenRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Citizen with ID " + id + " does not exist"));
        CitizenDto citizenDto = new CitizenDto();
        BeanUtils.copyProperties(citizen, citizenDto);
        return citizenDto;
    }

    @Override
    public void deleteCitizen(UUID id) {
        citizenRepository.deleteById(id);
    }

    @Override
    public List<CitizenDto> getAllCitizens() {
        return citizenRepository.findAll().stream()
                .map(citizen -> {
                    CitizenDto dto = new CitizenDto();
                    BeanUtils.copyProperties(citizen, dto);
                    return dto;
                }).collect(Collectors.toList());
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
