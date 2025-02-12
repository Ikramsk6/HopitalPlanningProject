package com.example.HopitalPlanningProject.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Planning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Roulement> roulements = new ArrayList<>();

    @OneToMany
    private List<Besoin> besoins = new ArrayList<>();

    private int nbMaxRoulement;
    private int tailleRoulement;

    @OneToMany
    private List<Preference> preferences = new ArrayList<>();

     public void ajouterRoulement(Roulement roulement) {
        this.roulements.add(roulement);
    }

     public void ajouterBesoin(Besoin besoin) {
        this.besoins.add(besoin);
    }

     public void genererPlanning() {
        System.out.println("Génération du planning...");
        // Implémentation future
    }

     public List<Roulement> genererLesRoulements() {
        System.out.println("Génération des roulements...");
        return new ArrayList<>(); // Implémentation future
    }

     public void modifierShift(int idPersonne, Shift nouveauShift) {
        System.out.println("Modification du shift pour la personne ID: " + idPersonne);
        // Implémentation future
    }

     public void afficherPlanning() {
        System.out.println("Affichage du planning...");
        // Implémentation future
    }

     public int calculerNbPersonneShift() {
        return 0; // Implémentation future
    }

     public int calculerNbJoursTravaillee() {
        return 0; // Implémentation future
    }

     public int verifierNbWEtravaillee() {
        return 0; // Implémentation future
    }

     public boolean verifierJourRepos() {
        return false; // Implémentation future
    }

     public boolean verifierShiftImpossible() {
        return false; // Implémentation future
    }
}
