package com.digitalidentityapi.citizen.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CitizenSubscription extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @ManyToOne
    @JoinColumn(name = "digital_identity_service_id", nullable = false)
    private DigitalIdentityServices digitalIdentityServices;

    @Temporal(TemporalType.TIMESTAMP)
    private Date subscriptionDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date unsubscriptionDate;

    @Column(nullable = false)
    private boolean isActive;
}