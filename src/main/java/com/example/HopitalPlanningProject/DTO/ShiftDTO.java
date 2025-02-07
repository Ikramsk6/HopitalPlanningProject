package com.example.HopitalPlanningProject.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShiftDTO {
    private Long id;
    private String nom;
    private String type; // Travail ou Repos
}
