package com.digitalidentityapi.citizen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Schema(name = "DigitalIdentityService", description = "Schema to hold Digital Identity Service information")
public class DigitalIdentityServicesDto {

    @Schema(description = "Unique identifier of the digital identity service", required = true)
    private int id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 2, max = 100)
    @Schema(description = "Name of the digital identity service", required = true)
    private String name;

    @Schema(description = "Indicates whether the service is premium", required = true)
    private Boolean isPremium;

    @Schema(description = "Date of record creation", required = true)
    private Date createdAt;

    @Schema(description = "Date of last update")
    private Date updatedAt;

    @Schema(description = "Date of record deletion")
    private Date deletedAt;
}
