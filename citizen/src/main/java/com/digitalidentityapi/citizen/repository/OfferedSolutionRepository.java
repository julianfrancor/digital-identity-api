package com.digitalidentityapi.citizen.repository;

import com.digitalidentityapi.citizen.entity.OfferedSolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferedSolutionRepository extends JpaRepository<OfferedSolution, Integer> {
}

