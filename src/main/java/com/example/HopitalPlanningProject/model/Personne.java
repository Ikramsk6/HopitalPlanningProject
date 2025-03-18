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
    private int idPersonne;

    private String nom;
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "idContrat")
    private Contrat contrat;

    @ManyToOne
    @JoinColumn(name = "idEquipe")
    private Equipe equipe;
}
