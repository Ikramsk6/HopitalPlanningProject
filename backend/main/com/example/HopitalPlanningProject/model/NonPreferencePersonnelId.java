package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class NonPreferencePersonnelId implements Serializable {

    @Column(name = "idPersonne")
    private int idPersonne;

    @Column(name = "idShift")
    private int idShift;

    @Column(name = "idJour")
    private int idJour;

}
