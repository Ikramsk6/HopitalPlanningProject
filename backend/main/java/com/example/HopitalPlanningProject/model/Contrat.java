package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrat")
    private int idContrat;

    @Column(name = "pourcentage_travail")
    private double pourcentageTravail; // Ex: 100.00, 80.00

    @Column(name = "description_contrat", nullable = false)
    private String descriptionContrat;

    @OneToOne
    @JoinColumn(name = "id_roulement", nullable = true, unique = true)
    private Roulement roulement;
}
