package com.digitalidentityapi.citizen.controller;

import com.digitalidentityapi.citizen.dto.DigitalIdentityServicesDto;
import com.digitalidentityapi.citizen.service.IDigitalIdentityServicesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizen-service/digital-identity-services")
public class DigitalIdentityServicesController {

    private final IDigitalIdentityServicesService digitalIdentityServicesService;

    public DigitalIdentityServicesController(IDigitalIdentityServicesService digitalIdentityServicesService) {
        this.digitalIdentityServicesService = digitalIdentityServicesService;
    }

    @PostMapping
    public ResponseEntity<DigitalIdentityServicesDto> createService(@RequestBody DigitalIdentityServicesDto serviceDto) {
        DigitalIdentityServicesDto createdService = digitalIdentityServicesService.createService(serviceDto);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DigitalIdentityServicesDto> updateService(@PathVariable int id, @RequestBody DigitalIdentityServicesDto serviceDto) {
        DigitalIdentityServicesDto updatedService = digitalIdentityServicesService.updateService(id, serviceDto);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable int id) {
        digitalIdentityServicesService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<DigitalIdentityServicesDto>> getAllServices() {
        List<DigitalIdentityServicesDto> services = digitalIdentityServicesService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DigitalIdentityServicesDto> getServiceById(@PathVariable int id) {
        DigitalIdentityServicesDto service = digitalIdentityServicesService.getServiceById(id);
        return ResponseEntity.ok(service);
    }
}
