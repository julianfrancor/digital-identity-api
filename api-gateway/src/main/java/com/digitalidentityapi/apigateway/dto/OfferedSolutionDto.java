package com.digitalidentityapi.apigateway.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OfferedSolutionDto {
    private int id;
    private String name;
    private int externalCompanyId;
    private String nit;
    private String address;
    private String email;
    private String phone;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
}
