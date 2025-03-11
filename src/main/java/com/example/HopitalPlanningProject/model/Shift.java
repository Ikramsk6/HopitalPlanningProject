package com.example.HopitalPlanningProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Représente un shift dans le système.
 * Utilise Lombok pour générer les getters, setters, et autres méthodes utiles.
 */
@Entity
@Data
public class Shift {
    /**
     * L'identifiant unique du shift.
     */
    @Id
    private int idShift;

    /**
     * Indique si le shift est un travail.
     */
    private boolean travail;

    /**
     * Le tag du shift.
     */
    private String tag;

    /**
     * Le type de shift.
     */
    private String type;
}