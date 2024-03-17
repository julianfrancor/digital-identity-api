package com.digitalidentityapi.brokerintermediary.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ExternalCompanyDto {
    private int id;
    private int externalCompanyId;
    private int digitalIdentityServiceId;
    private Date affiliationDate;
    private Date dischargeDate;
}
