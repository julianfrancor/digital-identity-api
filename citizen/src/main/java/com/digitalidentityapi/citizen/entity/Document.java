package com.digitalidentityapi.citizen.entity;

import com.digitalidentityapi.citizen.enums.IdentificationType;
import com.digitalidentityapi.citizen.enums.Status;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Document extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private UUID citizenId;

    @Column(nullable = false)
    private UUID documentTypeId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String metadata; // JSON data as a string
}

