package com.example.HopitalPlanningProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Représente un contrat dans le système.
 * Utilise Lombok pour générer les getters, setters, et autres méthodes utiles.
 */
@Entity
@Data
public class Contrat {
    /**
     * L'identifiant unique du contrat.
     */
    @Id
    private int idContrat;

    /**
     * Le montant du contrat.
     */
    private BigDecimal montantContrat;

    /**
     * La description du contrat.
     */
    private String descriptionContrat;
}