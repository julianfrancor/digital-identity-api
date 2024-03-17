package com.digitalidentityapi.brokerintermediary.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Schema(name = "citizen", description = "Schema to hold Citizen information")
public class CitizenDto {

    @Schema(description = "Unique identifier of the citizen", required = false)
    private int id;

    @NotEmpty(message = "Identification cannot be empty")
    @Schema(description = "Identification number of the citizen", required = true)
    private String identification;

    @NotEmpty
    @Schema(description = "Type of identification document", required = true)
    private String identificationType;

    @NotEmpty
    @Size(min = 2, max = 100)
    @Schema(description = "First name of the citizen", required = true)
    private String firstName;

    @Size(max = 100)
    @Schema(description = "Second name of the citizen")
    private String secondName;

    @NotEmpty
    @Size(min = 2, max = 100)
    @Schema(description = "Last name of the citizen", required = true)
    private String lastName;

    @Size(max = 100)
    @Schema(description = "Second last name of the citizen")
    private String secondLastName;

    @Schema(description = "Address of the citizen")
    private String address;

    @Email
    @NotEmpty
    @Schema(description = "Email address of the citizen", required = true)
    private String email;

    @Pattern(regexp = "^(\\+?\\d{1,3}?[- .]?)?(\\(?\\d{2,3}?\\)?[- .]?)?\\d{7,10}$", message = "Invalid phone number")
    @Schema(description = "Phone number of the citizen")
    private String phone;

    @NotEmpty
    @Schema(description = "Status of the citizen", required = true)
    private String status;

    @Schema(description = "Date of record creation", required = true)
    private Date createdAt;

    @Schema(description = "Date of last update")
    private Date updatedAt;

    @Schema(description = "Date of record deletion")
    private Date deletedAt;
}
