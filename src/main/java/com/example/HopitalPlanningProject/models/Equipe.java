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
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int tailleEquipe;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Personne> listePersonnes = new ArrayList<>();

    // Ajouter une personne dans l'équipe
    public void ajouterPersonne(Personne p) {
        listePersonnes.add(p);
        p.setEquipe(this); // Assure que la personne est liée à cette équipe
        this.tailleEquipe = listePersonnes.size(); // Mise à jour de la taille
    }

    // Supprimer une personne de l'équipe
    public void supprimerPersonne(Personne p) {
        listePersonnes.remove(p);
        p.setEquipe(null); // Assure que la personne n'est plus liée à cette équipe
        this.tailleEquipe = listePersonnes.size(); // Mise à jour de la taille
    }
}