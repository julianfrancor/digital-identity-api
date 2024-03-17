package com.digitalidentityapi.brokerintermediary.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CitizenDto {
    private String identification;

    private String identificationType;

    private String firstName;

    private String secondName;

    private String lastName;

    private String secondLastName;

    private String address;

    private String email;

    private String phone;

    private String status;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;
}
