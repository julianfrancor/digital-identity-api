package com.digitalidentityapi.brokerintermediary.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TransferDto {
    private int id;

    private String citizenId;

    private String citizenEmail;

    private String operatorId;

    private Date transferDate;

    private Date createdAt;

    private Date updatedAt;

    private Date deletedAt;
}
