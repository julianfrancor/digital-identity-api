package com.digitalidentityapi.citizen.repository;

import com.digitalidentityapi.citizen.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

    /**
     * Finds all documents associated with a given citizen's email.
     *
     * @param email the email of the citizen whose documents are to be retrieved
     * @return a list of documents associated with the specified citizen's email
     */
    List<Document> findAllByCitizenEmail(String email);

    /**
     * Finds a document by its ID.
     *
     * @param id the unique identifier of the document
     * @return the document matching both the specified ID and document type ID, if found
     */
    Document findDocumentById (int id);

    /**
     * Finds documents filtered by the document type ID and the associated citizen's email.
     * This method enables the retrieval of specific documents of a given type that are associated with a particular citizen,
     * facilitating targeted document management and access within the system.
     *
     * @param documentTypeId The UUID of the document type by which to filter documents.
     * @param citizenEmail The email of the citizen associated with the documents to be retrieved.
     * @return A list of Document entities that match the specified document type ID and are associated with the given citizen's email.
     *         The list may be empty if no documents match the given criteria.
     */
    @Query("SELECT d FROM Document d JOIN d.citizen c WHERE d.documentTypeId = :documentTypeId AND c.email = :citizenEmail")
    List<Document> findByDocumentTypeIdAndCitizenEmail(UUID documentTypeId, String citizenEmail);
}
