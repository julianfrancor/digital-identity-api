package com.digitalidentityapi.brokerintermediary.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/citizens", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BrokerIntermediaryController {

    private final ICitizenService citizenService;

    @Autowired
    public BrokerIntermediaryController(ICitizenService citizenService) {
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

    @PutMapping("/update")
    public ResponseEntity<CitizenDto> updateCitizen(@RequestParam String email, @RequestBody CitizenDto citizenDto) {
        citizenService.updateCitizen(email, citizenDto);
        return ResponseEntity.ok(citizenDto);
    }

    @GetMapping("/find-by-email")
    public ResponseEntity<CitizenDto> getCitizenByEmail(@RequestParam String email) {
        CitizenDto citizenDto = citizenService.getCitizenByEmail(email);
        return ResponseEntity.ok(citizenDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCitizen(@RequestParam String email) {
        citizenService.deleteCitizen(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CitizenDto>> searchCitizens(@RequestParam String criteria) {
        List<CitizenDto> citizens = citizenService.searchCitizens(criteria);
        return ResponseEntity.ok(citizens);
    }

    // Restore a soft-deleted citizen
    @PatchMapping("/restore")
    public ResponseEntity<Void> restoreCitizen(@RequestParam String email) {
        citizenService.restoreCitizen(email);
        return ResponseEntity.ok().build();
    }

    // Register a citizen for a premium service
    @PostMapping("/register")
    public ResponseEntity<Void> registerCitizenForPremiumService(@RequestParam String email, @RequestParam String serviceId) {
        citizenService.registerCitizenForPremiumService(email, serviceId);
        return ResponseEntity.ok().build();
    }

    // Unregister a citizen from a premium service
    @DeleteMapping("/unregister")
    public ResponseEntity<Void> unregisterCitizenFromPremiumService(@RequestParam String email, @RequestParam String serviceId) {
        citizenService.unregisterCitizenFromPremiumService(email, serviceId);
        return ResponseEntity.ok().build();
    }

    // Transfer a citizen to a different operator
    @PostMapping("/transfer")
    public ResponseEntity<Void> transferCitizen(@RequestParam String email, @RequestParam String targetOperatorId) {
        citizenService.transferCitizen(email, targetOperatorId);
        return ResponseEntity.ok().build();
    }

    // Verify a citizen's identity
    @GetMapping("/verify")
    public ResponseEntity<Boolean> verifyCitizenIdentity(@RequestParam String email) {
        boolean isVerified = citizenService.verifyCitizenIdentity(email);
        return ResponseEntity.ok(isVerified);
    }
}
