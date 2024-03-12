package com.digitalidentityapi.citizen.service.impl;

import com.digitalidentityapi.citizen.dto.CitizenSubscriptionDto;
import com.digitalidentityapi.citizen.entity.Citizen;
import com.digitalidentityapi.citizen.entity.CitizenSubscription;
import com.digitalidentityapi.citizen.entity.DigitalIdentityServices;
import com.digitalidentityapi.citizen.mapper.CitizenSubscriptionMapper;
import com.digitalidentityapi.citizen.repository.CitizenRepository;
import com.digitalidentityapi.citizen.repository.CitizenSubscriptionRepository;
import com.digitalidentityapi.citizen.repository.DigitalIdentityServicesRepository;
import com.digitalidentityapi.citizen.service.ICitizenSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitizenSubscriptionServiceImpl implements ICitizenSubscriptionService {

    @Autowired
    private CitizenSubscriptionRepository citizenSubscriptionRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private DigitalIdentityServicesRepository digitalIdentityServicesRepository;

    @Override
    public CitizenSubscriptionDto subscribeService(String citizenEmail, int digitalIdentityServiceId) {
        Citizen citizen = citizenRepository.findByEmail(citizenEmail).orElseThrow(() ->
                new IllegalStateException("Citizen with Email " + citizenEmail + " does not exist"));
        DigitalIdentityServices digitalIdentityServices = digitalIdentityServicesRepository.
                findById(digitalIdentityServiceId).orElseThrow(() ->
                        new IllegalStateException("DigitalIdentityService with ID " + digitalIdentityServiceId + " does not exist"));
        CitizenSubscription citizenSubscription = new CitizenSubscription();
        citizenSubscription.setCitizen(citizen);
        citizenSubscription.setDigitalIdentityServices(digitalIdentityServices);
        citizenSubscription.setSubscriptionDate(new Date());
        citizenSubscription.setActive(true);
        citizenSubscription.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        citizenSubscription.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        CitizenSubscription savedCitizenSubscription = citizenSubscriptionRepository.save(citizenSubscription);
        return CitizenSubscriptionMapper.toDto(savedCitizenSubscription, citizenEmail);
    }

    @Override
    public void unsubscribeService(String citizenEmail, int digitalIdentityServiceId) {
        Citizen citizen = citizenRepository.findByEmail(citizenEmail).orElseThrow(() ->
                new IllegalStateException("Citizen with Email " + citizenEmail + " does not exist"));
        CitizenSubscription citizenSubscription = citizenSubscriptionRepository.findByCitizenIdAndDigitalIdentityServicesId(
                citizen.getId(), digitalIdentityServiceId).orElseThrow(() ->
                new RuntimeException("Subscription not found with id: " + citizen.getId()));
        citizenSubscription.setUnsubscriptionDate(new Date());
        citizenSubscription.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        citizenSubscription.setActive(false);
        citizenSubscriptionRepository.save(citizenSubscription);
    }

    @Override
    public List<CitizenSubscriptionDto> findSubscriptionsByCitizen(String citizenEmail) {
        Citizen citizen = citizenRepository.findByEmail(citizenEmail).orElseThrow(() ->
                new IllegalStateException("Citizen with Email " + citizenEmail + " does not exist"));
        List<CitizenSubscription> subscriptions = citizenSubscriptionRepository.findByCitizenId(citizen.getId());
        return subscriptions.stream()
                .map(subscription -> CitizenSubscriptionMapper.toDto(subscription, citizenEmail))
                .collect(Collectors.toList());
    }
}
