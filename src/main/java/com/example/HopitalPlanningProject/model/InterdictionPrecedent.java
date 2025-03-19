package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class InterdictionPrecedent {
    @EmbeddedId
    private InterdictionPrecedentId id;

    // Getters, setters et autres méthodes
}

