package com.digitalidentityapi.citizen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
@Schema(name = "ExternalCompanies", description = "Schema to hold External Companies information")
public class ExternalCompaniesDto {

    @NotEmpty(message = "ID cannot be empty")
    @Schema(description = "Unique identifier of the external company", required = true)
    private String id;

    @NotEmpty(message = "Name cannot be empty")
    @Schema(description = "Name of the external company", required = true)
    private String name;

    @NotEmpty(message = "NIT cannot be empty")
    @Schema(description = "Tax identification number of the external company", required = true)
    private String nit;

    @Schema(description = "Address of the external company")
    private String address;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    @Schema(description = "Email address of the external company", required = true)
    private String email;

    @NotEmpty(message = "Phone cannot be empty")
    @Schema(description = "Phone number of the external company", required = true)
    private String phone;

    @Schema(description = "Date of record creation", required = true)
    private Date createdAt;

    @Schema(description = "Date of last update")
    private Date updatedAt;

    @Schema(description = "Date of record deletion")
    private Date deletedAt;
}
