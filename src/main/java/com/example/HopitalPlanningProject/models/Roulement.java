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
public class Roulement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "roulement_shifts",
            joinColumns = @JoinColumn(name = "roulement_id"),
            inverseJoinColumns = @JoinColumn(name = "shift_id")
    )
    private List<Shift> shifts = new ArrayList<>();

    private int dureeRoulement;

    @ElementCollection
    private List<String> contraintes = new ArrayList<>();

    private int nbMaxRoulement;
    private int nbMaxTailleRoulement;

    public boolean verifierContraintes() {
        return !contraintes.isEmpty();
    }


    public void ajouterShift(Shift shift) {
        this.shifts.add(shift);
    }


    public void genererRoulement() {
        System.out.println("Génération du roulement...");
        // Ici, on implémentera l'algorithme pour générer un roulement
    }
}
