package com.digitalidentityapi.citizen.service.impl;

import com.digitalidentityapi.citizen.dto.DocumentDto;
import com.digitalidentityapi.citizen.entity.Citizen;
import com.digitalidentityapi.citizen.entity.Document;
import com.digitalidentityapi.citizen.mapper.DocumentMapper;
import com.digitalidentityapi.citizen.repository.CitizenRepository;
import com.digitalidentityapi.citizen.repository.DocumentRepository;
import com.digitalidentityapi.citizen.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements IDocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private CitizenRepository citizenRepository;

    @Override
    public DocumentDto createDocument(DocumentDto documentDto) {
        Citizen citizen = citizenRepository.findByEmail(documentDto.getCitizenEmail()).orElseThrow(() ->
                new IllegalStateException("Citizen with Email " + documentDto.getCitizenEmail() + " does not exist"));
        Document document = DocumentMapper.toEntity(documentDto, new Document(), citizen);
        document.setCreatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        document.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        Document savedDocument = documentRepository.save(document);
        return DocumentMapper.toDto(savedDocument);
    }

    @Override
    public DocumentDto updateDocument(int id, DocumentDto documentDto) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        document.setDocumentTypeId(documentDto.getDocumentTypeId());
        document.setTitle(documentDto.getTitle());
        document.setUrl(documentDto.getUrl());
        document.setUpdatedAt(LocalDateTime.now(ZoneId.systemDefault()));
        Document updatedDocument = documentRepository.save(document);
        return DocumentMapper.toDto(updatedDocument);
    }

    @Override
    public DocumentDto updateDocumentURL(int id, String url) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        document.setUrl(url);
        Document updatedDocument = documentRepository.save(document);
        return DocumentMapper.toDto(updatedDocument);

    }

    @Override
    public DocumentDto getDocumentById(int id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return DocumentMapper.toDto(document);
    }

    @Override
    public void deleteDocument(int id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        documentRepository.delete(document);
    }

    @Override
    public List<DocumentDto> getAllDocumentsByCitizenEmail(String email) {
        List<Document> documents = documentRepository.findAllByCitizenEmail(email);
        return documents.stream()
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DocumentDto findDocumentById(int id) {
        Document document = documentRepository.findDocumentById(id);
        return DocumentMapper.toDto(document);
    }

    @Override
    public List<DocumentDto> findDocumentByDocumentTypeIdAndCitizenEmail(String documentTypeId, String citizenEmail) {
        List<Document> documents = documentRepository.findByDocumentTypeIdAndCitizenEmail(UUID.fromString(documentTypeId), citizenEmail);

        return documents.stream()
                .map(DocumentMapper::toDto)
                .collect(Collectors.toList());
    }
}
