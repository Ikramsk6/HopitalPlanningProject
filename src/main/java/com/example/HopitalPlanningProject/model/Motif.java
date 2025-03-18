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
    private String idMotif;

    private int nbMinApparitionMotif;
    private int nbMaxApparitionMotif;
}
