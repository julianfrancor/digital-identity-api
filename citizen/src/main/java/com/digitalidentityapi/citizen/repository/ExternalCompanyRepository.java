package com.digitalidentityapi.citizen.repository;

import com.digitalidentityapi.citizen.entity.ExternalCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalCompanyRepository extends JpaRepository<ExternalCompany, Integer> {
}
