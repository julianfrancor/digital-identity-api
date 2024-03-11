package com.digitalidentityapi.citizen.mapper;

import com.digitalidentityapi.citizen.dto.DocumentDto;
import com.digitalidentityapi.citizen.entity.Document;
import com.digitalidentityapi.citizen.entity.Citizen;

import static com.digitalidentityapi.citizen.utils.Utils.convertToDate;


public class DocumentMapper {

    public static DocumentDto mapToDocumentDto(Document document) {
        if (document == null) {
            throw new IllegalArgumentException("Cannot map a null Document to DocumentDto.");
        }
        DocumentDto documentDto = new DocumentDto();

        documentDto.setId(document.getId());
        documentDto.setCitizenEmail(document.getCitizen() != null ? document.getCitizen().getEmail() : null);
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
}

