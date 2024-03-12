package com.digitalidentityapi.citizen.repository;

import com.digitalidentityapi.citizen.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

    // Find a citizen by their email
    Optional<Citizen> findByEmail(String email);

    // Find a citizen by their identification number
    Optional<Citizen> findByIdentification(String identification);
}

