package com.digitalidentityapi.citizen.Message;

import com.digitalidentityapi.citizen.dto.CitizenWithDocumentsTransferInfoDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Data
public class TransferCitizenMessage {
    private String destinationOperatorId;
    private CitizenWithDocumentsTransferInfoDTO citizenWithDocumentsTransferInfoDTO;

    public TransferCitizenMessage(String destinationOperatorId, CitizenWithDocumentsTransferInfoDTO citizenWithDocumentsTransferInfoDTO) {
        this.destinationOperatorId = destinationOperatorId;
        this.citizenWithDocumentsTransferInfoDTO = citizenWithDocumentsTransferInfoDTO;
    }
}
