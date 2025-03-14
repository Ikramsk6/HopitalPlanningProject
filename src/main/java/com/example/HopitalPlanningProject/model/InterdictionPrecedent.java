package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

/**
 * Représente une interdiction de shift précédent.
 */
@Entity
@Data
@IdClass(InterdictionPrecedentId.class)
public class InterdictionPrecedent {

    @Id
    @ManyToOne
    @JoinColumn(name = "idShift")
    private ShiftPoste shift;

    @Id
    @ManyToOne
    @JoinColumn(name = "idShift_1")
    private ShiftPoste shift1;
}
