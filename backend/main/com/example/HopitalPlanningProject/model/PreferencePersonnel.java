package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PreferencePersonnel {
    @EmbeddedId
    private PreferencePersonnelId id;

    @ManyToOne
    @MapsId("idPersonne") // Utilise l'id de la clé composite
    @JoinColumn(name = "idPersonne", nullable = false)
    private Personne personne;
}


