package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

/**
 * Représente une préférence générale.
 */
@Entity
@Data
@IdClass(PreferenceGeneraleId.class)
public class PreferenceGenerale {

    @Id
    @ManyToOne
    @JoinColumn(name = "idContrat")
    private Contrat contrat;

    @Id
    @ManyToOne
    @JoinColumn(name = "idShift")
    private ShiftPoste shift;

    @Id
    @ManyToOne
    @JoinColumn(name = "idJour")
    private Jour jour;

    /**
     * Nombre maximum de différences acceptées.
     */
    private int nbMaxDifference;
}
