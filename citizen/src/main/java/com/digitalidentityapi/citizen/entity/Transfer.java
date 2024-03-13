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
public class Transfer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "citizen_id", nullable = false)
    private int citizenId;

    @Column(name = "operator_id", nullable = false)
    private int operatorId;

    @Column(name = "transfer_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date transferDate;
}
