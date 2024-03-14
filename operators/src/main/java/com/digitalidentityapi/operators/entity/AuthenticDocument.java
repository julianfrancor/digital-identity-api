package com.digitalidentityapi.operators.entity;

import lombok.*;

public class AuthenticDocument {
    private String idCitizen;
    private String urlDocument;
    private String documentTitle;

    public AuthenticDocument(String idCitizen, String urlDocument, String documentTitle) {
        this.idCitizen = idCitizen;
        this.urlDocument = urlDocument;
        this.documentTitle = documentTitle;
    }

    public String getIdCitizen() {
        return idCitizen;
    }

    public void setIdCitizen(String idCitizen) {
        this.idCitizen = idCitizen;
    }

    public String getUrlDocument() {
        return urlDocument;
    }

    public void setUrlDocument(String urlDocument) {
        this.urlDocument = urlDocument;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }
}
