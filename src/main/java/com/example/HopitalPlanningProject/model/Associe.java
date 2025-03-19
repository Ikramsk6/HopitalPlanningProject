package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Associe {
    @EmbeddedId
    private AssocieId id;



    // Getters, setters et autres méthodes
}
