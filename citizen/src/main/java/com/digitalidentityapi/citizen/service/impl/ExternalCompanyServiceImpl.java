package com.digitalidentityapi.citizen.service.impl;

import com.digitalidentityapi.citizen.dto.ExternalCompanyDto;
import com.digitalidentityapi.citizen.entity.ExternalCompany;
import com.digitalidentityapi.citizen.mapper.ExternalCompanyMapper;
import com.digitalidentityapi.citizen.repository.ExternalCompanyRepository;
import com.digitalidentityapi.citizen.service.IExternalCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalCompanyServiceImpl implements IExternalCompanyService {

    @Autowired
    private ExternalCompanyRepository externalCompanyRepository;

    @Override
    public ExternalCompanyDto createExternalCompany(ExternalCompanyDto externalCompanyDto) {
        ExternalCompany externalCompany = ExternalCompanyMapper.toEntity(externalCompanyDto);
        externalCompany = externalCompanyRepository.save(externalCompany);
        return ExternalCompanyMapper.toDto(externalCompany);
    }

    @Override
    public ExternalCompanyDto updateExternalCompany(int id, ExternalCompanyDto externalCompanyDto) {
        ExternalCompany externalCompany = externalCompanyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ExternalCompany with ID " + id + " not found"));
        ExternalCompanyMapper.updateEntityFromDto(externalCompanyDto, externalCompany);
        externalCompany = externalCompanyRepository.save(externalCompany);
        return ExternalCompanyMapper.toDto(externalCompany);
    }

    @Override
    public void deleteExternalCompany(int id) {
        externalCompanyRepository.deleteById(id);
    }

    @Override
    public ExternalCompanyDto getExternalCompanyById(int id) {
        ExternalCompany externalCompany = externalCompanyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ExternalCompany with ID " + id + " not found"));
        return ExternalCompanyMapper.toDto(externalCompany);
    }

    @Override
    public List<ExternalCompanyDto> getAllExternalCompanies() {
        return externalCompanyRepository.findAll().stream()
                .map(ExternalCompanyMapper::toDto)
                .collect(Collectors.toList());
    }
}
