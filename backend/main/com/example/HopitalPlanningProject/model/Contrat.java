package com.example.HopitalPlanningProject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "pourcentage_travail")
    private double pourcentageTravail; // Ex: 100.00, 80.00

    @Column(name = "description_contrat", nullable = false)
    private String descriptionContrat;

    @OneToOne
    @JoinColumn(name = "id_roulement", nullable = true, unique = true)
    private Roulement roulement;

    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference  // Sérialisation de la liste des personnes
    private List<Personne> personnes = new ArrayList<>();
}
