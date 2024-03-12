package com.digitalidentityapi.citizen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "Transfers", description = "Schema to hold Transfers information")
public class TransfersDto {
    @NotEmpty(message = "ID cannot be empty")
    @Schema(description = "Unique identifier of the transfer", required = true)
    private int id;

    @NotEmpty(message = "Citizen ID cannot be empty")
    @Schema(description = "Identifier of the citizen being transferred", required = true)
    private String citizenId;

    @NotEmpty(message = "Citizen email cannot be empty")
    @Schema(description = "Email of the citizen to whom the document belongs", required = true)
    private String citizenEmail;

    @NotEmpty(message = "Operator ID cannot be empty")
    @Schema(description = "Identifier of the operator", required = true)
    private String operatorId;

    @NotEmpty(message = "Transfer date cannot be empty")
    @Schema(description = "Date of the transfer", required = true)
    private Date transferDate;

    @Schema(description = "Date of record creation", required = true)
    private Date createdAt;

    @Schema(description = "Date of last update")
    private Date updatedAt;

    @Schema(description = "Date of record deletion")
    private Date deletedAt;
}
