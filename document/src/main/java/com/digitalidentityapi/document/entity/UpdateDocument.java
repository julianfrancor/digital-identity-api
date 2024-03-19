package com.digitalidentityapi.document.entity;

public class UpdateDocument {

    private String citizenEmail;
    private String documentTypeId;
    private String title;
    private String url;


    public UpdateDocument(String citizenEmail, String documentTypeId, String title, String url) {
        this.citizenEmail = citizenEmail;
        this.documentTypeId = documentTypeId;
        this.title = title;
        this.url = url;
    }

    @Override
    public String toString() {
        return "{" +
                "\"operation\":\"CREATE\"," +
                "\"documentDto\":{" +
                "\"citizenEmail\": \"" + citizenEmail + "\"," +
                "\"documentTypeId\": \"" + documentTypeId + "\"," +
                "\"title\": \"" + title + "\"," +
                "\"url\":\"" + url + "\"," +
                "\"metadata\": {\"autor\": \"Siste\", \"fecha\": \"2024-03-10\"}}}";
    }

}
