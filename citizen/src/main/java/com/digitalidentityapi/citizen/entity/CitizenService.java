package com.digitalidentityapi.citizen.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CitizenService extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizenId;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private DigitalIdentityService serviceId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date subscriptionDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date unsubscriptionDate;
}