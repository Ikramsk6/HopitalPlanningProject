package com.example.HopitalPlanningProject.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferenceDTO {
    private Long id;
    private String jour;
    private Long shiftId;
    private int ecart;
}
