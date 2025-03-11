package com.example.HopitalPlanningProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Représente un roulement dans le système.
 * Utilise Lombok pour générer les getters, setters, et autres méthodes utiles.
 */
@Entity
@Data
public class Roulement {
    /**
     * L'identifiant unique du roulement.
     */
    @Id
    private int idRoulement;

    /**
     * La taille du roulement.
     */
    private String tailleRoulement;
}