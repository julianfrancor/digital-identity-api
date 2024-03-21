package com.digitalidentityapi.apigateway.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CitizenSubscriptionDto {
    private int id;
    private int digitalIdentityServiceId;
    private String citizenEmail;
    private String serviceName;
    private int citizenId;
    private Date subscriptionDate;
    private Boolean isActive;
    private Date unsubscriptionDate;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
