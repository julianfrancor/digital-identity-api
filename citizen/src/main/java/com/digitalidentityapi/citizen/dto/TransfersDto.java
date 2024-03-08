package com.digitalidentityapi.citizen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Schema(name = "transfers", description = "Schema to hold Transfers information")
public class TransfersDto {
    @NotEmpty(message = "ID cannot be empty")
    @Schema(description = "Unique identifier of the transfer", required = true)
    private String id;

    @NotEmpty(message = "Citizen ID cannot be empty")
    @Schema(description = "Identifier of the citizen being transferred", required = true)
    private String citizen_id;

    @NotEmpty(message = "Operator ID cannot be empty")
    @Schema(description = "Identifier of the operator", required = true)
    private String operator_id;

    @NotEmpty(message = "Transfer date cannot be empty")
    @Schema(description = "Date of the transfer", required = true)
    private Date transfer_date;

    @Schema(description = "Date of record creation", required = true)
    private Date created_at;

    @Schema(description = "Date of last update")
    private Date updated_at;

    @Schema(description = "Date of record deletion")
    private Date deleted_at;
}
