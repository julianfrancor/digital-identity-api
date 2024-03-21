package com.digitalidentityapi.citizen.controller;

import com.digitalidentityapi.citizen.dto.CitizenSubscriptionDto;
import com.digitalidentityapi.citizen.service.ICitizenSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/citizen-service/citizen-subscriptions")
public class CitizenSubscriptionController {

    private final ICitizenSubscriptionService citizenSubscriptionService;

    @Autowired
    public CitizenSubscriptionController(ICitizenSubscriptionService citizenSubscriptionService) {
        this.citizenSubscriptionService = citizenSubscriptionService;
    }

    @PostMapping
    public ResponseEntity<CitizenSubscriptionDto> subscribeService(@RequestParam String citizenEmail, @RequestParam int digitalIdentityServiceId) {
        CitizenSubscriptionDto createdSubscription = citizenSubscriptionService.subscribeService(citizenEmail, digitalIdentityServiceId);
        return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Void> unsubscribeService(@RequestParam String citizenEmail, @RequestParam int digitalIdentityServiceId) {
        citizenSubscriptionService.unsubscribeService(citizenEmail, digitalIdentityServiceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CitizenSubscriptionDto>> findSubscriptionsByCitizen(@RequestParam String citizenEmail) {
        List<CitizenSubscriptionDto> subscriptions = citizenSubscriptionService.findSubscriptionsByCitizen(citizenEmail);
        return ResponseEntity.ok(subscriptions);
    }
}
