package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;

@Entity
public class NonAffinite {
    @EmbeddedId
    private NonAffiniteId id;

    // Getters, setters et autres méthodes
}


