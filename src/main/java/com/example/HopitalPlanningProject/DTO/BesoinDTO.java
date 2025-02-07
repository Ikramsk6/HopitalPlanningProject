package com.example.HopitalPlanningProject.DTO;

import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@Getter
@Setter
public class BesoinDTO {
    private Long id;
    private Map<String, int[]> besoinsParShift;
}
