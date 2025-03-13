package com.example.HopitalPlanningProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * Représente une personne dans le système.
 * Utilise Lombok pour générer les getters, setters, et autres méthodes utiles.
 */
@Entity
@Data
public class Planning {
    /**
     * L'identifiant unique de la personne.
     */
    @Id
    private int idPersonne;

    /**
     * Le nom de la personne.
     */
    private String nom;

    /**
     * Le prénom de la personne.
     */
    private String prenom;

    /**
     * Le contrat associé à la personne.
     */
    @ManyToOne
    @JoinColumn(name = "idContrat")
    private Contrat contrat;

    /**
     * L'équipe associée à la personne.
     */
    @ManyToOne
    @JoinColumn(name = "idEquipe")
    private Equipe equipe;
}