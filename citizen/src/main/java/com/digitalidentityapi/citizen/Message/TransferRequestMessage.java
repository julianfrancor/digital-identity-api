package com.digitalidentityapi.citizen.Message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferRequestMessage {
    private String citizenIdentification;
    private String citizenEmail;
    private String destinationOperatorId;

    public TransferRequestMessage() {
    }

    @JsonCreator
    public TransferRequestMessage(@JsonProperty("citizenIdentification") String citizenIdentification,
                                  @JsonProperty("citizenEmail") String citizenEmail,
                                  @JsonProperty("destinationOperatorId") String destinationOperatorId) {
        this.citizenIdentification = citizenIdentification;
        this.citizenEmail = citizenEmail;
        this.destinationOperatorId = destinationOperatorId;
    }

    // Getters y setters
    public String getCitizenIdentification() {
        return citizenIdentification;
    }

    public void setCitizenIdentification(String citizenIdentification) {
        this.citizenIdentification = citizenIdentification;
    }

    public String getCitizenEmail() {
        return citizenEmail;
    }

    public void setCitizenEmail(String citizenEmail) {
        this.citizenEmail = citizenEmail;
    }

    public String getDestinationOperatorId() {
        return destinationOperatorId;
    }

    public void setDestinationOperatorId(String destinationOperatorId) {
        this.destinationOperatorId = destinationOperatorId;
    }
}
