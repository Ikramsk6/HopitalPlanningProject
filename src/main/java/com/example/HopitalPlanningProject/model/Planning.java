package com.example.HopitalPlanningProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Représente un planning dans le système.
 * Utilise Lombok pour générer les getters, setters, et autres méthodes utiles.
 */
@Entity
@Data
public class Planning {
    /**
     * L'identifiant unique du planning.
     */
    @Id
    private int idPlanning;

    /**
     * Le nombre maximum de semaines dans le planning.
     */
    private byte nbSemaineMax;

    /**
     * Le nombre maximum de roulements dans le planning.
     */
    private byte nbRoulementMax;
}