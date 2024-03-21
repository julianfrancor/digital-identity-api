package com.digitalidentityapi.operators.dto;

import jdk.jfr.DataAmount;


public class CitizenForTransferDTO {
    private String destinationOperatorId;
    private CitizenWithDocumentsTransferInfoDTO citizenWithDocumentsTransferInfoDTO;

    public String getDestinationOperatorId() {
        return destinationOperatorId;
    }

    public void setDestinationOperatorId(String destinationOperatorId) {
        this.destinationOperatorId = destinationOperatorId;
    }

    public CitizenWithDocumentsTransferInfoDTO getCitizenWithDocumentsTransferInfoDTO() {
        return citizenWithDocumentsTransferInfoDTO;
    }

    public void setCitizenWithDocumentsTransferInfoDTO(CitizenWithDocumentsTransferInfoDTO citizenWithDocumentsTransferInfoDTO) {
        this.citizenWithDocumentsTransferInfoDTO = citizenWithDocumentsTransferInfoDTO;
    }
}
