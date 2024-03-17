package com.digitalidentityapi.brokerintermediary.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@Schema(name = "ExternalCompany", description = "Information about external companies associated with digital identity services.")
public class ExternalCompanyDto {

    @Schema(description = "The unique identifier of the external company.", required = true)
    private int id;

    @Schema(description = "Identifier for the external company within its system.", required = true)
    private int externalCompanyId;

    @Schema(description = "Identifier of the associated digital identity service.", required = true)
    private int digitalIdentityServiceId;

    @Schema(description = "Date when the company was affiliated.", required = true)
    private Date affiliationDate;

    @Schema(description = "Date when the company's affiliation was terminated.", required = false)
    private Date dischargeDate;
}
