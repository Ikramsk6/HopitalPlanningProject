package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
public class PreferencePersonnel {

    // Getter et Setter pour l'ID
    @EmbeddedId
    private PreferencePersonnelId id;

}
