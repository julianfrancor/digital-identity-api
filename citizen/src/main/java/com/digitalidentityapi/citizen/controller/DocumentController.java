package com.digitalidentityapi.citizen.controller;

import com.digitalidentityapi.citizen.dto.CitizenDto;
import com.digitalidentityapi.citizen.dto.DocumentDto;
import com.digitalidentityapi.citizen.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private IDocumentService documentService;

    @PostMapping
    public ResponseEntity<DocumentDto> createDocument(@RequestBody DocumentDto documentDto) {
        DocumentDto createdDocument = documentService.createDocument(documentDto);
        return new ResponseEntity<>(createdDocument, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocumentById(@PathVariable int id) {
        DocumentDto document = documentService.getDocumentById(id);
        return ResponseEntity.ok(document);
    }

    @GetMapping("")
    public ResponseEntity<List<DocumentDto>> getAllDocuments(@RequestParam String email) {
        List<DocumentDto> documents = documentService.getAllDocumentsByCitizenEmail(email);
        return ResponseEntity.ok(documents);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DocumentDto> updateDocument(@PathVariable int id, @RequestBody DocumentDto documentDto) {
        DocumentDto updatedDocument = documentService.updateDocument(id, documentDto);
        return ResponseEntity.ok(updatedDocument);
    }

    @PutMapping("/update-url/{id}")
    public ResponseEntity<DocumentDto> updateDocumentURL(@PathVariable int id, @RequestParam String url) {
        DocumentDto updatedDocument = documentService.updateDocumentURL(id, url);
        return ResponseEntity.ok(updatedDocument);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable int id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/citizen")
    public ResponseEntity<List<DocumentDto>> getAllDocumentsByCitizenEmail(@RequestParam String citizenEmail) {
        List<DocumentDto> documents = documentService.getAllDocumentsByCitizenEmail(citizenEmail);
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/search")
    public ResponseEntity<List<DocumentDto>> findDocumentByDocumentTypeIdAndCitizenEmail(
            @RequestParam String documentTypeId, @RequestParam String citizenEmail) {
        List<DocumentDto> documents = documentService.findDocumentByDocumentTypeIdAndCitizenEmail(documentTypeId, citizenEmail);
        return ResponseEntity.ok(documents);
    }
}
