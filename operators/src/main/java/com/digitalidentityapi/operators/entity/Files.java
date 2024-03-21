package com.digitalidentityapi.operators.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Files {
    @JsonProperty("documentTitle")
    private String documentTitle;
    @JsonProperty("UrlDocument")
    private String UrlDocument;

    public Files(String documentTitle, String urlDocument) {
        this.documentTitle = documentTitle;
        UrlDocument = urlDocument;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getUrlDocument() {
        return UrlDocument;
    }

    public void setUrlDocument(String urlDocument) {
        UrlDocument = urlDocument;
    }
}
