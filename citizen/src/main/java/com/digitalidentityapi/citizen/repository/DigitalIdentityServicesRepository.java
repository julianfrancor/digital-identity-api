package com.digitalidentityapi.citizen.repository;

import com.digitalidentityapi.citizen.entity.DigitalIdentityServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DigitalIdentityServicesRepository extends JpaRepository<DigitalIdentityServices, Integer> {

    Optional<DigitalIdentityServices> findById(int id);

    void deleteById(int serviceId);
}

