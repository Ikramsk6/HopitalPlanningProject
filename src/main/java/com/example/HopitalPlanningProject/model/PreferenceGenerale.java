package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class PreferenceGenerale {
    @EmbeddedId
    private PreferenceGeneraleId id;

    private short nbMaxDifférence; // Maximum difference (e.g., max difference in shifts)

    // Getters, setters et autres méthodes
}

