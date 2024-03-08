package com.digitalidentityapi.citizen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Schema(name = "document_type", description = "Schema to hold Document Type information")
public class DocumentTypeDto {

    @NotEmpty(message = "ID cannot be empty")
    @Schema(description = "Unique identifier of the document type", required = true)
    private String id;

    @NotEmpty(message = "Type cannot be empty")
    @Schema(description = "Type of the document", required = true)
    private String type;

    @Schema(description = "Date of record creation", required = true)
    private Date createdAt;

    @Schema(description = "Date of last update")
    private Date updatedAt;

    @Schema(description = "Date of record deletion")
    private Date deletedAt;
}
