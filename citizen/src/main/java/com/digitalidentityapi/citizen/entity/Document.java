package com.digitalidentityapi.citizen.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "citizen_id", referencedColumnName = "id")
    private Citizen citizen;

    @Column(nullable = false)
    private String documentTypeId;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String url;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String metadata;
}
