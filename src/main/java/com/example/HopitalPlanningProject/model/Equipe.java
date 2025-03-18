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
}
