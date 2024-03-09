package com.digitalidentityapi.citizen.service;

import com.digitalidentityapi.citizen.dto.CitizenDto;

import java.util.List;
import java.util.UUID;

public interface ICitizenService {

    /**
     * Creates a new citizen record in the system.
     *
     * @param citizenDto the data transfer object containing citizen details
     */
    void createCitizen(CitizenDto citizenDto);

    /**
     * Updates an existing citizen's information.
     *
     * @param email         the email of the citizen to update
     * @param citizenDto the data transfer object containing updated citizen details
     */
    void updateCitizen(String email, CitizenDto citizenDto);

    /**
     * Retrieves a citizen's information by their email.
     *
     * @param email of the citizen to retrieve
     * @return the data transfer object containing the citizen's details
     */
    CitizenDto getCitizenByEmail(String email);

    /**
     * Deletes a citizen's record from the system.
     *
     * @param id the unique identifier of the citizen to delete
     */
    void deleteCitizen(String email);

    /**
     * Retrieves a list of all citizens in the system.
     *
     * @return a list of data transfer objects containing the details of all citizens
     */
    List<CitizenDto> getAllCitizens();

    /**
     * Searches for citizens based on a given criteria or filter.
     *
     * @param criteria the search criteria to filter citizens
     * @return a list of data transfer objects matching the search criteria
     */
    List<CitizenDto> searchCitizens(String criteria);

    /**
     * Restores a previously deleted citizen's record.
     *
     * @param id the unique identifier of the citizen to restore
     */
    void restoreCitizen(UUID id);

    /**
     * Registers a citizen for a specific service.
     *
     * @param citizenId the unique identifier of the citizen
     * @param serviceId the unique identifier of the service to register the citizen for
     */
    void registerCitizenForPremiumService(UUID citizenId, UUID serviceId);

    /**
     * Unregisters a citizen from a specific service.
     *
     * @param citizenId the unique identifier of the citizen
     * @param serviceId the unique identifier of the service to unregister the citizen from
     */
    void unregisterCitizenFromPremiumService(UUID citizenId, UUID serviceId);

    /**
     * Transfers a citizen to a different operator.
     *
     * @param citizenId       the unique identifier of the citizen to transfer
     * @param targetOperatorId the unique identifier of the target operator to transfer the citizen to
     */
    void transferCitizen(UUID citizenId, UUID targetOperatorId);

    /**
     * Verifies the identity of a citizen.
     *
     * @param citizenId the unique identifier of the citizen whose identity is to be verified
     * @return true if the identity is verified, false otherwise
     */
    boolean verifyCitizenIdentity(UUID citizenId);
}

