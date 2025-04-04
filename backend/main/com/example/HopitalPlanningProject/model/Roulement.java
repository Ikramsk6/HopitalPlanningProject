package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

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
    private int idRoulement;

    @Column(name = "Taille_Roulement", nullable = false)
    private byte tailleRoulement;

    // Utilisation de @Transient si tu ne veux pas que cette liste soit persistée en base
    @Transient
    private List<Integer> planningRepos;
}
