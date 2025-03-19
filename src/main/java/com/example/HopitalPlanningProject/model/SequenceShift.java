package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class SequenceShift {
    @EmbeddedId
    private SequenceShiftId id;


    private int ordre;

    // Getters, setters et autres méthodes
}

