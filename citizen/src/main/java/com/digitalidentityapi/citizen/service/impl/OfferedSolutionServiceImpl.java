package com.digitalidentityapi.citizen.service.impl;

import com.digitalidentityapi.citizen.dto.OfferedSolutionDto;
import com.digitalidentityapi.citizen.entity.ExternalCompany;
import com.digitalidentityapi.citizen.entity.OfferedSolution;
import com.digitalidentityapi.citizen.mapper.OfferedSolutionMapper;
import com.digitalidentityapi.citizen.repository.ExternalCompanyRepository;
import com.digitalidentityapi.citizen.repository.OfferedSolutionRepository;
import com.digitalidentityapi.citizen.service.IOfferedSolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferedSolutionServiceImpl implements IOfferedSolutionService {

    private final OfferedSolutionRepository offeredSolutionRepository;

    private final ExternalCompanyRepository externalCompanyRepository;

    @Autowired
    public OfferedSolutionServiceImpl(
            OfferedSolutionRepository offeredSolutionRepository,
            ExternalCompanyRepository externalCompanyRepository) {
        this.offeredSolutionRepository = offeredSolutionRepository;
        this.externalCompanyRepository = externalCompanyRepository;
    }

    @Override
    public OfferedSolutionDto createOfferedSolution(OfferedSolutionDto offeredSolutionDto) {
        ExternalCompany externalCompany = externalCompanyRepository.findById(offeredSolutionDto.getExternalCompanyId()).orElseThrow(() ->
                new IllegalStateException("ExternalCompany with Id " + offeredSolutionDto.getExternalCompanyId() + " does not exist"));
        OfferedSolution offeredSolution = OfferedSolutionMapper.toEntity(offeredSolutionDto, externalCompany);
        offeredSolution.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        offeredSolution.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        offeredSolution = offeredSolutionRepository.save(offeredSolution);
        return OfferedSolutionMapper.toDto(offeredSolution);
    }

    @Override
    public OfferedSolutionDto updateOfferedSolution(int id, OfferedSolutionDto offeredSolutionDto) {
        ExternalCompany externalCompany = externalCompanyRepository.findById(offeredSolutionDto.getExternalCompanyId()).orElseThrow(() ->
                new IllegalStateException("ExternalCompany with Id " + offeredSolutionDto.getExternalCompanyId() + " does not exist"));
        OfferedSolution existingOfferedSolution = offeredSolutionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OfferedSolution with ID " + id + " not found"));
        OfferedSolutionMapper.updateEntity(existingOfferedSolution, offeredSolutionDto, externalCompany);
        existingOfferedSolution.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        existingOfferedSolution = offeredSolutionRepository.save(existingOfferedSolution);
        return OfferedSolutionMapper.toDto(existingOfferedSolution);
    }

    @Override
    public void deleteOfferedSolution(int id) {
        offeredSolutionRepository.deleteById(id);
    }

    @Override
    public OfferedSolutionDto getOfferedSolutionById(int id) {
        OfferedSolution offeredSolution = offeredSolutionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OfferedSolution with ID " + id + " not found"));
        return OfferedSolutionMapper.toDto(offeredSolution);
    }

    @Override
    public List<OfferedSolutionDto> getAllOfferedSolutions() {
        return offeredSolutionRepository.findAll().stream()
                .map(OfferedSolutionMapper::toDto)
                .collect(Collectors.toList());
    }
}
