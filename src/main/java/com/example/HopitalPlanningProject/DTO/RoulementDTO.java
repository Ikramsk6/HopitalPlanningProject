package com.example.HopitalPlanningProject.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RoulementDTO {
    private Long id;
    private List<ShiftDTO> shifts;
    private int dureeRoulement;
}
