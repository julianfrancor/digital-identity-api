package com.digitalidentityapi.citizen.service;

import com.digitalidentityapi.citizen.dto.DigitalIdentityServicesDto;
import java.util.List;

public interface IDigitalIdentityServicesService {
    DigitalIdentityServicesDto createService(DigitalIdentityServicesDto digitalIdentityServicesDto);
    DigitalIdentityServicesDto updateService(int digitalIdentityServiceId, DigitalIdentityServicesDto digitalIdentityServicesDto);
    void deleteService(int digitalIdentityServiceId);
    List<DigitalIdentityServicesDto> getAllServices();
    DigitalIdentityServicesDto getServiceById(int digitalIdentityServiceId);
}
