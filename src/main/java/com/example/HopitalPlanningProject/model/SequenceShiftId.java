package com.example.HopitalPlanningProject.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class SequenceShiftId implements Serializable {
    private int idRoulement;
    private int idShift;

    // Constructeurs, getters, setters, equals, hashCode
}
