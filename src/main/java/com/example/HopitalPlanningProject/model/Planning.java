package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Planning {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlanning;

    private String nomPlanning;
}
