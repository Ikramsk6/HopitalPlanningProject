package com.example.HopitalPlanningProject.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PlanningDTO {
    private Long id;
    private List<RoulementDTO> roulements;
    private List<BesoinDTO> besoins;
    private List<String> contraintes;
}
