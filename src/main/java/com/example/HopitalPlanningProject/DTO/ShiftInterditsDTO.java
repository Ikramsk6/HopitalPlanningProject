package com.example.HopitalPlanningProject.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShiftInterditsDTO extends ContrainteDTO {
    private List<Long> shiftIds;
}
