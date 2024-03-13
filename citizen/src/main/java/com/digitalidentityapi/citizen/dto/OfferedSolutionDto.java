package com.digitalidentityapi.citizen.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class OfferedSolutionDto {
    @Schema(description = "The unique identifier of the external company service.", required = true)
    private int id;
    @Schema(description = "The name of the external company.", required = true)
    private String name;
    @Schema(description = "The unique identifier of the external company.", required = true)
    private int externalCompanyId;
    @Schema(description = "The nit of the external company.", required = true)
    private String nit;
    @Schema(description = "The address of the external company.", required = true)
    private String address;
    @Schema(description = "The email of the external company.", required = true)
    private String email;
    @Schema(description = "The phone of the external company.", required = true)
    private String phone;
    @Schema(description = "The createdAt of the external company.", required = true)
    private Date createdAt;
    @Schema(description = "The updatedAt of the external company.", required = true)
    private Date updatedAt;
    @Schema(description = "The deletedAt of the external company.", required = true)
    private Date deletedAt;
}
