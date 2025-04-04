package com.example.HopitalPlanningProject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Planning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPlanning; // Primary Key

    private byte nbSemaineMax;
    private byte nbRoulementMax;
    private int nbJoursMaxTravailConsecutifs;
    private int nbJoursMinTravailConsecutifs;
    private int frequenceWeekend;
}
