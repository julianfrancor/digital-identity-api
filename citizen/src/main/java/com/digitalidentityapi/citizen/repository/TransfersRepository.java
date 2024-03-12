package com.digitalidentityapi.citizen.repository;

import com.digitalidentityapi.citizen.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfersRepository extends JpaRepository<Transfer, Integer> {

}
