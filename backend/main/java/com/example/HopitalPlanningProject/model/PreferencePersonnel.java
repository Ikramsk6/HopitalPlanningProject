package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
public class PreferencePersonnel {
    @EmbeddedId
    private PreferencePersonnelId id;



}

