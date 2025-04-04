package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;

@Entity
public class Comporte {

    @EmbeddedId
    private ComporteId id;

    // Getters, setters et autres méthodes
}

