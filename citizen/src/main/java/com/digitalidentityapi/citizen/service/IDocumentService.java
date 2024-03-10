package com.digitalidentityapi.citizen.service;


import com.digitalidentityapi.citizen.dto.DocumentDto;
import com.digitalidentityapi.citizen.entity.Document;

import java.util.List;
import java.util.UUID;

public interface IDocumentService {

    /**
     * Creates a new document record associated with a citizen.
     *
     * @param documentDto the data transfer object containing document details
     * @return the created Document DTO
     */
    DocumentDto createDocument(DocumentDto documentDto);

    /**
     * Updates an existing document's information.
     *
     * @param id          the unique identifier of the document to update
     * @param documentDto the data transfer object containing updated document details
     * @return the updated Document DTO
     */
    DocumentDto updateDocument(int id, DocumentDto documentDto);

    /**
     * Updates an existing document's url.
     *
     * @param id          the unique identifier of the document to update
     * @param url where the document is stored
     * @return the updated Document DTO
     */
    DocumentDto updateDocumentURL(int id, String url);

    /**
     * Retrieves a document by its ID.
     *
     * @param id the unique identifier of the document to retrieve
     * @return the Document DTO
     */
    DocumentDto getDocumentById(int id);

    /**
     * Deletes a document record from the system.
     *
     * @param id the unique identifier of the document to delete
     */
    void deleteDocument(int id);

    /**
     * Retrieves all documents associated with a citizen.
     *
     * @param email of the citizen whose documents are to be retrieved
     * @return a list of Document DTOs
     */
    List<DocumentDto> getAllDocumentsByCitizenEmail(String email);

    /**
     * Retrieves a document by its unique identifier.
     *
     * @param id The unique identifier of the document to be retrieved.
     * @return A DocumentDto containing the document details, or null if no document is found with the given ID.
     */
    DocumentDto findDocumentById(int id);

    /**
     * Retrieves a list of documents filtered by the document type ID and associated citizen's email.
     *
     * This method is useful for finding specific documents of a certain type belonging to a particular citizen.
     *
     * @param documentTypeId The unique identifier of the document type to filter documents.
     * @param citizenEmail The email of the citizen associated with the documents to be retrieved.
     * @return A list of DocumentDto objects containing the details of documents matching the specified document type ID and associated with the given citizen's email. The list may be empty if no matching documents are found.
     */
    List<DocumentDto> findDocumentByDocumentTypeIdAndCitizenEmail(String documentTypeId, String citizenEmail);
}
