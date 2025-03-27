package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersonne")
    private int idPersonne;

    @Column(name = "Nom", nullable = false)
    private String nom;

    @Column(name = "Prénom", nullable = false)
    private String prenom;

    @Column(name = "actif", nullable = false)
    private boolean actif;

    @ManyToOne
    @JoinColumn(name = "idContrat", nullable = false)
    private Contrat contrat;
}
