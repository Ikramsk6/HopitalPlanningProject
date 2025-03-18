package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShiftPoste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idShift;

    private boolean travail;
    private String tag;
    private String type;
    private String poste;
}
