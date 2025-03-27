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
    @Column(name = "idContrat")
    private int idContrat;

    @Column(name = "__contrat")
    private double pourcentageTravail; // Ex: 100.00, 80.00

    @Column(name = "Description_contrat", nullable = false)
    private String descriptionContrat;

    @OneToOne
    @JoinColumn(name = "Id_Roulement", nullable = false, unique = true)
    private Roulement roulement;
}
