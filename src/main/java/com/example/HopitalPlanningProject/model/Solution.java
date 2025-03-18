package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idPersonne")
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "idRoulement")
    private Roulement roulement;

    private int semaineDebut;
}
