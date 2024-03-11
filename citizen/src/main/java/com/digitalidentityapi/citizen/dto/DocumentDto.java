package com.digitalidentityapi.citizen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "Document", description = "Schema to hold Document information")
public class DocumentDto {

    @Schema(description = "Unique identifier of the document", required = false)
    private int id;

    @NotEmpty(message = "Citizen email cannot be empty")
    @Schema(description = "Email of the citizen to whom the document belongs", required = true)
    private String citizenEmail;

    @NotEmpty(message = "Document type ID cannot be empty")
    @Schema(description = "Identifier of the document type", required = true)
    private String documentTypeId;

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 1, max = 255)
    @Schema(description = "Title of the document", required = true)
    private String title;

    @NotEmpty(message = "URL cannot be empty")
    @Schema(description = "URL of the document", required = true)
    private String url;

    @Schema(description = "Metadata of the document")
    private String metadata;

    @Schema(description = "Date of record creation", required = true)
    private Date createdAt;

    @Schema(description = "Date of last update")
    private Date updatedAt;

    @Schema(description = "Date of record deletion")
    private Date deletedAt;
}
