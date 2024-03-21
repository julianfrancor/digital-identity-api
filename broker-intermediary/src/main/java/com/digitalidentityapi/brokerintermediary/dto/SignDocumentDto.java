package com.digitalidentityapi.brokerintermediary.dto;

import lombok.Data;

@Data
public class SignDocumentDto {
    private Long idCitizen;
    private String UrlDocument;
    private String documentTitle;
    private String email;
}
