package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class Solution {
    @EmbeddedId
    private SolutionId id;

    private int semaineDebut;

    // Getters, setters et autres méthodes
}
