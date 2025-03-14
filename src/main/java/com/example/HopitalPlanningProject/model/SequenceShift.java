package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

/**
 * Représente une séquence de shift.
 */
@Entity
@Data
@IdClass(SequenceShiftId.class)
public class SequenceShift {

    @Id
    @ManyToOne
    @JoinColumn(name = "idRoulement")
    private Roulement roulement;

    @Id
    @ManyToOne
    @JoinColumn(name = "idShift")
    private ShiftPoste shift;

    /**
     * Ordre du shift dans la séquence.
     */
    private int ordre;
}
