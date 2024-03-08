package com.digitalidentityapi.citizen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
@Schema(name = "CitizenServices", description = "Schema to hold Citizen Services information")
public class CitizenServicesDto {

    @NotEmpty(message = "ID cannot be empty")
    @Schema(description = "Unique identifier of the citizen service", required = true)
    private String id;

    @NotEmpty(message = "Digital Identity Service ID cannot be empty")
    @Schema(description = "Digital Identity Service identifier", required = true)
    private String digitalIdentityServiceId;

    @NotEmpty(message = "Citizen ID cannot be empty")
    @Schema(description = "Citizen identifier", required = true)
    private String citizenId;

    @Schema(description = "Subscription date to the service")
    private Date subscriptionDate;

    @Schema(description = "Unsubscription date from the service")
    private Date unsubscriptionDate;

    @Schema(description = "Date of record creation")
    private Date createdAt;

    @Schema(description = "Date of last update")
    private Date updatedAt;

    @Schema(description = "Date of record deletion")
    private Date deletedAt;
}
