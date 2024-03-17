package com.digitalidentityapi.brokerintermediary.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Schema(name = "citizen_subscription", description = "Schema to hold Citizen Subscription Services information")
public class CitizenSubscriptionDto {

    @NotEmpty(message = "ID cannot be empty")
    @Schema(description = "Unique identifier of the citizen service", required = true)
    private int id;

    @NotEmpty(message = "Digital Identity Service ID cannot be empty")
    @Schema(description = "Digital Identity Service identifier", required = true)
    private int digitalIdentityServiceId;

    @NotEmpty(message = "Citizen email cannot be empty")
    @Schema(description = "Email of the citizen to whom the document belongs", required = true)
    private String citizenEmail;

    @NotEmpty(message = "Service Subscribed name")
    @Schema(description = "Name of the subscribed service", required = true)
    private String serviceName;

    @NotEmpty(message = "Citizen ID cannot be empty")
    @Schema(description = "Citizen identifier", required = true)
    private int citizenId;

    @Schema(description = "Subscription date to the service")
    private Date subscriptionDate;

    @Schema(description = "Indicates whether the subscription is active", required = true)
    private Boolean isActive;

    @Schema(description = "Unsubscription date from the service")
    private Date unsubscriptionDate;

    @Schema(description = "Date of record creation")
    private Date createdAt;

    @Schema(description = "Date of last update")
    private Date updatedAt;

    @Schema(description = "Date of record deletion")
    private Date deletedAt;
}
