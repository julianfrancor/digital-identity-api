package com.digitalidentityapi.apigateway.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CitizenDto {
    private String identification;
    private String identificationType;
    private String fullName;
    private String address;
    private String email;
    private String phone;
    private String status;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
