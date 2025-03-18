package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

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

    private String tailleRoulement;
}
