package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;

@Entity
public class Solution {
    @EmbeddedId
    private SolutionId id;

    private int semaineDebut;

    // Getters, setters et autres méthodes
}
