package com.digitalidentityapi.citizen.service.impl;

import com.digitalidentityapi.citizen.dto.DocumentDto;
import com.digitalidentityapi.citizen.entity.Document;
import com.digitalidentityapi.citizen.repository.DocumentRepository;
import com.digitalidentityapi.citizen.service.IDocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements IDocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    private ModelMapper modelMapper;

    @Override
    public DocumentDto createDocument(DocumentDto documentDto) {
        Document document = modelMapper.map(documentDto, Document.class);
        Document savedDocument = documentRepository.save(document);
        return modelMapper.map(savedDocument, DocumentDto.class);
    }

    @Override
    public DocumentDto updateDocument(int id, DocumentDto documentDto) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        modelMapper.map(documentDto, document);
        Document updatedDocument = documentRepository.save(document);
        return modelMapper.map(updatedDocument, DocumentDto.class);
    }

    @Override
    public DocumentDto updateDocumentURL(int id, String url) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        document.setUrl(url);
        Document updatedDocument = documentRepository.save(document);
        return modelMapper.map(updatedDocument, DocumentDto.class);
    }

    @Override
    public DocumentDto getDocumentById(int id) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        return modelMapper.map(document, DocumentDto.class);
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
                .map(document -> modelMapper.map(document, DocumentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public DocumentDto findDocumentById(int id) {
        Document document = documentRepository.findDocumentById(id);
        return modelMapper.map(document, DocumentDto.class);
    }

    @Override
    public List<DocumentDto> findDocumentByDocumentTypeIdAndCitizenEmail(String documentTypeId, String citizenEmail) {
        List<Document> documents = documentRepository.findByDocumentTypeIdAndCitizenEmail(UUID.fromString(documentTypeId), citizenEmail);

        return documents.stream()
                .map(document -> modelMapper.map(document, DocumentDto.class))
                .collect(Collectors.toList());
    }
}
