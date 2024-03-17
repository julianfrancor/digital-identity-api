package com.digitalidentityapi.brokerintermediary.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class DigitalIdentityServicesDto {

    private int id;

    private String name;

    private Boolean isPremium;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;
}
