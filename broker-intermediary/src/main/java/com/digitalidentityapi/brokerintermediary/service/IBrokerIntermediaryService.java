package com.digitalidentityapi.brokerintermediary.service;

import com.digitalidentityapi.citizen.dto.CitizenDto;

import java.util.List;

public interface IBrokerIntermediaryService {

    void createCitizen(CitizenDto citizenDto);
    void updateCitizen(String email, CitizenDto citizenDto);
    CitizenDto getCitizenByEmail(String email);
    void deleteCitizen(String email);
    List<CitizenDto> getAllCitizens();
    List<CitizenDto> searchCitizens(String criteria);
    void restoreCitizen(String id);
    void registerCitizenForPremiumService(String citizenId, String serviceId);
    void unregisterCitizenFromPremiumService(String citizenId, String serviceId);
    void transferCitizen(String email, String targetOperatorId);
    boolean verifyCitizenIdentity(String email);
}

