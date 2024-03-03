package com.digitalidentityapi.citizen.entity;

import com.digitalidentityapi.citizen.enums.IdentificationType;
import com.digitalidentityapi.citizen.enums.Status;
import java.util.UUID;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Citizen extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String identification;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IdentificationType identificationType;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String secondName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String secondLastName;

    @Column(length = 500)
    private String address;

    @Email
    @Column(nullable = false)
    private String email;

    @Column
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
}

