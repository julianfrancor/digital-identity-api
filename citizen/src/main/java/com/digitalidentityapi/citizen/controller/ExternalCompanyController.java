package com.digitalidentityapi.citizen.controller;

import com.digitalidentityapi.citizen.dto.ExternalCompanyDto;
import com.digitalidentityapi.citizen.service.IExternalCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizen-service/external-companies")
public class ExternalCompanyController {

    private final IExternalCompanyService externalCompanyService;

    @Autowired
    public ExternalCompanyController(IExternalCompanyService externalCompanyService) {
        this.externalCompanyService = externalCompanyService;
    }

    @PostMapping
    public ResponseEntity<?> createExternalCompany(@RequestBody ExternalCompanyDto externalCompanyDto) {
        try {
            ExternalCompanyDto createdExternalCompany = externalCompanyService.createExternalCompany(externalCompanyDto);
            return new ResponseEntity<>(createdExternalCompany, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return handleDataIntegrityViolationException(e);
        }
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("externalCompanyId already created and has to be unique");
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred");
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExternalCompanyDto> updateExternalCompany(@PathVariable int id, @RequestBody ExternalCompanyDto externalCompanyDto) {
        ExternalCompanyDto updatedExternalCompany = externalCompanyService.updateExternalCompany(id, externalCompanyDto);
        return ResponseEntity.ok(updatedExternalCompany);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExternalCompanyDto> getExternalCompanyById(@PathVariable int id) {
        ExternalCompanyDto externalCompanyDto = externalCompanyService.getExternalCompanyById(id);
        return ResponseEntity.ok(externalCompanyDto);
    }

    @GetMapping
    public ResponseEntity<List<ExternalCompanyDto>> getAllExternalCompanies() {
        List<ExternalCompanyDto> allExternalCompanies = externalCompanyService.getAllExternalCompanies();
        return ResponseEntity.ok(allExternalCompanies);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExternalCompany(@PathVariable int id) {
        externalCompanyService.deleteExternalCompany(id);
        return ResponseEntity.noContent().build();
    }
}
