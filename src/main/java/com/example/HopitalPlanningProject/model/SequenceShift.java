package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class SequenceShift {
    @EmbeddedId
    private SequenceShiftId id;

    private int ordre;

    // Nouveau constructeur pour initialiser uniquement l'id
    public SequenceShift(SequenceShiftId id) {
        this.id = id;
    }
}
