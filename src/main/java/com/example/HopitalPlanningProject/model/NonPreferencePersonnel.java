package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
public class NonPreferencePersonnel {

    @EmbeddedId
    private NonPreferencePersonnelId id;

    public void setId(NonPreferencePersonnelId id) {
        this.id = id;
    }
}

