package com.digitalidentityapi.citizen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@Schema(name = "ExternalCompaniesServices", description = "Schema to hold External Companies Services information")
public class ExternalCompaniesServicesDto {

    @NotEmpty(message = "ID cannot be empty")
    @Schema(description = "Unique identifier of the external company service", required = true)
    private UUID id;

    @NotEmpty(message = "External company ID cannot be empty")
    @Schema(description = "Identifier of the external company", required = true)
    private UUID externalCompanyId;

    @NotEmpty(message = "Digital identity service ID cannot be empty")
    @Schema(description = "Identifier of the digital identity service", required = true)
    private UUID digitalIdentityServiceId;

    @Schema(description = "Affiliation date of the external company service")
    private Date affiliationDate;

    @Schema(description = "Discharge date of the external company service")
    private Date dischargeDate;

    @Schema(description = "Date of record creation", required = true)
    private Date createdAt;

    @Schema(description = "Date of last update")
    private Date updatedAt;

    @Schema(description = "Date of record deletion")
    private Date deletedAt;
}
