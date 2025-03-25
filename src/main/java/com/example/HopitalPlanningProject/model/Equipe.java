package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEquipe;

    private String nomEquipe;

    @OneToOne
    @JoinColumn(name = "idPlanning", unique = true, nullable = false)
    private Planning planning;

    // Méthodes appelées dans les tests

    public int getId() {
        return idEquipe;
    }

    public void setId(int id) {
        this.idEquipe = id;
    }

    public String getNom() {
        return nomEquipe;
    }

    public void setNom(String nom) {
        this.nomEquipe = nom;
    }
}
