package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InterdictionPrecedent {
    @EmbeddedId
    private InterdictionPrecedentId id;

    // Relation ManyToOne avec ShiftPoste
    @ManyToOne
    @JoinColumn(name = "idShift", insertable = false, updatable = false)
    private ShiftPoste shiftPoste;
}

