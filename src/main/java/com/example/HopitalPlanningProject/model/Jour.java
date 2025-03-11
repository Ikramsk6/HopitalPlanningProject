package com.example.HopitalPlanningProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Représente un jour dans le système.
 * Utilise Lombok pour générer les getters, setters, et autres méthodes utiles.
 */
@Entity
@Data
public class Jour {
    /**
     * L'identifiant unique du jour.
     */
    @Id
    private String idJour;

    /**
     * Le nom du jour.
     */
    private String nomJour;
}