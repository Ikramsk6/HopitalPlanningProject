package com.example.HopitalPlanningProject.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlanningDTO {
    private Long id;
    private List<Long> roulementIds;
    private List<Long> besoinIds;
    private int nbMaxRoulement;
    private int tailleRoulement;
    private List<Long> preferenceIds;
}
