package com.digitalidentityapi.citizen.service;

import com.digitalidentityapi.citizen.dto.CitizenSubscriptionDto;

import java.util.List;

/**
 * Service interface for managing citizen services subscriptions.
 */
public interface ICitizenSubscriptionService {
    /**
     * Subscribes a citizen to a digital identity service.
     *
     * @param citizenEmail The email of the citizen.
     * @param digitalIdentityServiceId The ID of the service.
     * @return The subscribed service.
     */
    CitizenSubscriptionDto subscribeService(String citizenEmail, int digitalIdentityServiceId);

    /**
     * Unsubscribes a citizen from a digital identity service.
     *
     * @param citizenEmail The email of the citizen service subscription.
     * @param digitalIdentityServiceId The ID of the service.
     */
    void unsubscribeService(String citizenEmail, int digitalIdentityServiceId);

    /**
     * Finds all services subscribed by a specific citizen.
     *
     * @param citizenEmail The ID of the citizen.
     * @return A list of services where the citizen is subscribed.
     */
    List<CitizenSubscriptionDto> findSubscriptionsByCitizen(String citizenEmail);
}
