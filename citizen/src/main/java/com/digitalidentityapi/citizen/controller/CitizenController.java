package com.digitalidentityapi.citizen.controller;

import com.digitalidentityapi.citizen.dto.CitizenDto;
import com.digitalidentityapi.citizen.service.ICitizenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/citizens", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CitizenController {

    private final ICitizenService citizenService;

    @Autowired
    public CitizenController(ICitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @GetMapping
    public ResponseEntity<List<CitizenDto>> getAllCitizens() {
        List<CitizenDto> citizens = citizenService.getAllCitizens();
        return ResponseEntity.ok(citizens);
    }

    @PostMapping
    public ResponseEntity<CitizenDto> createCitizen(@Valid @RequestBody CitizenDto citizenDto) {
        citizenService.createCitizen(citizenDto);
        return new ResponseEntity<>(citizenDto, HttpStatus.CREATED);
    }

    @PutMapping("/{email}")
    public ResponseEntity<CitizenDto> updateCitizen(@PathVariable String email, @RequestBody CitizenDto citizenDto) {
        citizenService.updateCitizen(email, citizenDto);
        return ResponseEntity.ok(citizenDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitizenDto> getCitizenById(@PathVariable UUID id) {
        CitizenDto citizenDto = citizenService.getCitizenById(id);
        return ResponseEntity.ok(citizenDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCitizen(@PathVariable UUID id) {
        citizenService.deleteCitizen(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CitizenDto>> searchCitizens(@RequestParam String criteria) {
        List<CitizenDto> citizens = citizenService.searchCitizens(criteria);
        return ResponseEntity.ok(citizens);
    }

    // Restore a soft-deleted citizen
    @PatchMapping("/restore/{id}")
    public ResponseEntity<Void> restoreCitizen(@PathVariable UUID id) {
        citizenService.restoreCitizen(id);
        return ResponseEntity.ok().build();
    }

    // Register a citizen for a premium service
    @PostMapping("/register/{citizenId}/{serviceId}")
    public ResponseEntity<Void> registerCitizenForPremiumService(@PathVariable UUID citizenId, @PathVariable UUID serviceId) {
        citizenService.registerCitizenForPremiumService(citizenId, serviceId);
        return ResponseEntity.ok().build();
    }

    // Unregister a citizen from a premium service
    @DeleteMapping("/unregister/{citizenId}/{serviceId}")
    public ResponseEntity<Void> unregisterCitizenFromPremiumService(@PathVariable UUID citizenId, @PathVariable UUID serviceId) {
        citizenService.unregisterCitizenFromPremiumService(citizenId, serviceId);
        return ResponseEntity.ok().build();
    }

    // Transfer a citizen to a different operator
    @PostMapping("/transfer/{citizenId}/{targetOperatorId}")
    public ResponseEntity<Void> transferCitizen(@PathVariable UUID citizenId, @PathVariable UUID targetOperatorId) {
        citizenService.transferCitizen(citizenId, targetOperatorId);
        return ResponseEntity.ok().build();
    }

    // Verify a citizen's identity
    @GetMapping("/verify/{citizenId}")
    public ResponseEntity<Boolean> verifyCitizenIdentity(@PathVariable UUID citizenId) {
        boolean isVerified = citizenService.verifyCitizenIdentity(citizenId);
        return ResponseEntity.ok(isVerified);
    }
}
