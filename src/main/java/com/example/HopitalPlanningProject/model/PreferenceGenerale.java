package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PreferenceGenerale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idContrat")
    private Contrat contrat;

    @ManyToOne
    @JoinColumn(name = "idShift")
    private ShiftPoste shift;

    @ManyToOne
    @JoinColumn(name = "idJour")
    private Jour jour;

    private int nbMaxDifference;
}
