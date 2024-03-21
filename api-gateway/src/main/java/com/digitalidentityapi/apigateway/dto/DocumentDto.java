package com.digitalidentityapi.apigateway.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DocumentDto {
    private int id;
    private String citizenEmail;
    private String documentTypeId;
    private String title;
    private String url;
    private String metadata;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
