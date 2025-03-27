package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Jour {
    @Id
    @Column(name = "idJour")
    private int idJour;

    @Column(name = "Nom_Jour", length = 50, nullable = false)
    private String nomJour;
}
