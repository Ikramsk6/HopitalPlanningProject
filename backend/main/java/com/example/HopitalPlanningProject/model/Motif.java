package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Motif {

    @Id
    private int idMotif; // Clé primaire

    private int nbMinApparitionMotif;
    private String nbMaxApparitionMotif; // Peut être une chaîne (ex. "max 5 fois")
}
