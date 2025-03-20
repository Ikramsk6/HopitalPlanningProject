package com.example.HopitalPlanningProject.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor
public class SequenceShiftId implements Serializable {
    private int idRoulement;
    private int idShift;

}
