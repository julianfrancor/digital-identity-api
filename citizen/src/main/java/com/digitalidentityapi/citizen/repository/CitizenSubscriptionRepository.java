package com.digitalidentityapi.citizen.repository;

import com.digitalidentityapi.citizen.entity.Citizen;
import com.digitalidentityapi.citizen.entity.CitizenSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CitizenSubscriptionRepository extends JpaRepository<CitizenSubscription, Integer> {

    Optional<CitizenSubscription> findByCitizenIdAndDigitalIdentityServicesId(int citizenSubscriptionId, int digitalIdentityServiceId);

    List<CitizenSubscription> findByCitizenId(int citizenId);
}

