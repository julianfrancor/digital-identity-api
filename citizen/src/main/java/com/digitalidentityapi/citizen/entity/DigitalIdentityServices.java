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
public class DigitalIdentityServices extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private boolean isPremium;
}
