package com.digitalidentityapi.citizen.service;

import com.digitalidentityapi.citizen.dto.ExternalCompanyDto;

import java.util.List;

public interface IExternalCompanyService {
    ExternalCompanyDto createExternalCompany(ExternalCompanyDto externalCompanyDto);
    ExternalCompanyDto updateExternalCompany(int id, ExternalCompanyDto externalCompanyDto);
    void deleteExternalCompany(int id);
    ExternalCompanyDto getExternalCompanyById(int id);
    List<ExternalCompanyDto> getAllExternalCompanies();
}
