package com.example.HopitalPlanningProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Représente un motif dans le système.
 */
@Entity
@Data
public class Motif {
    /**
     * L'identifiant unique du motif.
     */
    @Id
    private String idMotif;

    /**
     * Nombre minimum d'apparition du motif.
     */
    private int nbMinApparitionMotif;

    /**
     * Nombre maximum d'apparition du motif.
     */
    private int nbMaxApparitionMotif;
}
