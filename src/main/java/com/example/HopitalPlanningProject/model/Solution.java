package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

/**
 * Représente une solution d'affectation.
 */
@Entity
@Data
@IdClass(SolutionId.class)
public class Solution {

    @Id
    @ManyToOne
    @JoinColumn(name = "idPersonne")
    private Personne personne;

    @Id
    @ManyToOne
    @JoinColumn(name = "idRoulement")
    private Roulement roulement;

    /**
     * Semaine de début de l'affectation.
     */
    private int semaineDebut;
}
