package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class NonAffinite {
    @EmbeddedId
    private NonAffiniteId id;

    // Getters, setters et autres méthodes
}


