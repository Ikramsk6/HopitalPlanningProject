package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;

@Entity
public class NonPreferencePersonnel {
    @EmbeddedId
    private NonPreferencePersonnelId id;

}

