package com.digitalidentityapi.citizen.mapper;

import com.digitalidentityapi.citizen.dto.DocumentDto;
import com.digitalidentityapi.citizen.entity.Document;
import com.digitalidentityapi.citizen.entity.Citizen;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DocumentMapper {

    public static DocumentDto mapToDocumentDto(Document document) {
        if (document == null) {
            throw new IllegalArgumentException("Cannot map a null Document to DocumentDto.");
        }
        DocumentDto documentDto = new DocumentDto();

        documentDto.setId(String.valueOf(document.getId()));
        documentDto.setCitizenId(document.getCitizen() != null ? document.getCitizen().getId() : null);
        documentDto.setDocumentTypeId(document.getDocumentTypeId());
        documentDto.setTitle(document.getTitle());
        documentDto.setUrl(document.getUrl());
        documentDto.setMetadata(document.getMetadata());
        documentDto.setCreatedAt(convertToDate(document.getCreatedAt()));
        documentDto.setUpdatedAt(convertToDate(document.getUpdatedAt()));

        return documentDto;
    }

    public static Document mapToDocument(DocumentDto documentDto, Document document, Citizen citizen) {
        if(document == null) {
            document = new Document();
        }
        document.setCitizen(citizen);
        document.setDocumentTypeId(documentDto.getDocumentTypeId());
        document.setTitle(documentDto.getTitle());
        document.setUrl(documentDto.getUrl());
        document.setMetadata(documentDto.getMetadata());

        return document;
    }

    private static Date convertToDate(LocalDateTime localDateTime) {
        return java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}

