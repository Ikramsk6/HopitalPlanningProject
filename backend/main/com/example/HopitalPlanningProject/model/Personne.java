package com.example.HopitalPlanningProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

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
    @NotNull
    @JoinColumn(name = "idContrat", nullable = false)
    @JsonBackReference  // Empêche la sérialisation du contrat pour éviter la récursivité
    private Contrat contrat;

    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreferencePersonnel> preferences = new ArrayList<>();
}
