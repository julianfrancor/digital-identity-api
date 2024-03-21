package com.digitalidentityapi.brokerintermediary.Message;

import com.digitalidentityapi.brokerintermediary.dto.CitizenDto;
import com.digitalidentityapi.brokerintermediary.dto.SignDocumentDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignDocumentMessage {
    private Long idCitizen;
    private String urlDocument;
    private String documentTitle;
    private String email;

    public SignDocumentMessage(SignDocumentDto signDocumentDto) {
        this.idCitizen = signDocumentDto.getIdCitizen();
        this.urlDocument = signDocumentDto.getUrlDocument();
        this.documentTitle = signDocumentDto.getDocumentTitle();
        this.email = signDocumentDto.getEmail();
    }
}

