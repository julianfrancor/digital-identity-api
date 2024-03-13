package com.digitalidentityapi.citizen.mapper;

import com.digitalidentityapi.citizen.dto.CitizenSubscriptionDto;
import com.digitalidentityapi.citizen.entity.Citizen;
import com.digitalidentityapi.citizen.entity.CitizenSubscription;
import com.digitalidentityapi.citizen.entity.DigitalIdentityServices;

import static com.digitalidentityapi.citizen.utils.Utils.convertToDate;

public class CitizenSubscriptionMapper {

    public static CitizenSubscriptionDto toDto(CitizenSubscription subscription, String citizenEmail) {
        if (subscription == null) {
            return null;
        }
        return CitizenSubscriptionDto.builder()
                .id(subscription.getId())
                .citizenId(subscription.getCitizen().getId())
                .citizenEmail(citizenEmail)
                .serviceName(subscription.getDigitalIdentityServices().getName())
                .digitalIdentityServiceId(subscription.getDigitalIdentityServices().getId())
                .subscriptionDate(subscription.getSubscriptionDate())
                .unsubscriptionDate(subscription.getUnsubscriptionDate())
                .isActive(subscription.isActive())
                .createdAt(convertToDate(subscription.getCreatedAt()))
                .updatedAt(convertToDate(subscription.getUpdatedAt()))
                .build();
    }

    public static CitizenSubscription toEntity(CitizenSubscriptionDto dto, Citizen citizen, DigitalIdentityServices digitalIdentityServices) {
        if (dto == null) {
            return null;
        }
        CitizenSubscription subscription = new CitizenSubscription();
        subscription.setCitizen(citizen);
        subscription.setDigitalIdentityServices(digitalIdentityServices);
        subscription.setSubscriptionDate(dto.getSubscriptionDate());
        subscription.setUnsubscriptionDate(dto.getUnsubscriptionDate());
        return subscription;
    }
}
