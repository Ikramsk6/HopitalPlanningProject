package com.example.HopitalPlanningProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

/**
 * Représente une équipe dans le système.
 * Utilise Lombok pour générer les getters, setters, et autres méthodes utiles.
 */
@Entity
@Data
public class Equipe {
    /**
     * L'identifiant unique de l'équipe.
     */
    @Id
    private int idEquipe;

    /**
     * Le nom de l'équipe.
     */
    private String nomEquipe;

    /**
     * Le planning associé à l'équipe.
     */
    @OneToOne
    @JoinColumn(name = "idPlanning", unique = true, nullable = false)
    private Planning planning;
}