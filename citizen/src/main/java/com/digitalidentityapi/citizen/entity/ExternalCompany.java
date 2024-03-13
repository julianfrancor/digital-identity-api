package com.digitalidentityapi.citizen.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExternalCompany extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "external_company_id", nullable = false, unique = true)
    private int externalCompanyId;

    @Column(name = "digital_identity_service_id", nullable = false)
    private int digitalIdentityServiceId;

    @Column(name = "affiliation_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date affiliationDate;

    @Column(name = "discharge_date")
    @Temporal(TemporalType.DATE)
    private Date dischargeDate;
}
