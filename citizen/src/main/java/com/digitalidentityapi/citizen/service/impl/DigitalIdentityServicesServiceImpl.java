package com.digitalidentityapi.citizen.service.impl;

import com.digitalidentityapi.citizen.dto.DigitalIdentityServicesDto;
import com.digitalidentityapi.citizen.entity.DigitalIdentityServices;
import com.digitalidentityapi.citizen.mapper.DigitalIdentityServicesMapper;
import com.digitalidentityapi.citizen.repository.DigitalIdentityServicesRepository;
import com.digitalidentityapi.citizen.service.IDigitalIdentityServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DigitalIdentityServicesServiceImpl implements IDigitalIdentityServicesService {

    @Autowired
    private DigitalIdentityServicesRepository serviceRepository;

    @Override
    public DigitalIdentityServicesDto createService(DigitalIdentityServicesDto serviceDto) {
        DigitalIdentityServices service = DigitalIdentityServicesMapper.toEntity(serviceDto);
        service.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        service.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        service = serviceRepository.save(service);
        return DigitalIdentityServicesMapper.toDto(service);
    }

    @Override
    public DigitalIdentityServicesDto updateService(int serviceId, DigitalIdentityServicesDto serviceDto) {
        DigitalIdentityServices existingService = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Service not found with ID: " + serviceId));

        existingService.setName(serviceDto.getName());
        existingService.setPremium(serviceDto.getIsPremium());
        existingService.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        existingService = serviceRepository.save(existingService);
        return DigitalIdentityServicesMapper.toDto(existingService);
    }

    @Override
    public void deleteService(int serviceId) {
        serviceRepository.deleteById(serviceId);
    }

    @Override
    public List<DigitalIdentityServicesDto> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(DigitalIdentityServicesMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DigitalIdentityServicesDto getServiceById(int serviceId) {
        DigitalIdentityServices service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new IllegalArgumentException("Service not found with ID: " + serviceId));
        return DigitalIdentityServicesMapper.toDto(service);
    }
}
