package com.example.HopitalPlanningProject.controllers;


import com.example.HopitalPlanningProject.model.ShiftPoste;
import com.example.HopitalPlanningProject.services.ShiftPosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shiftsPostes")
public class ShiftPosteController {

    @Autowired
    private ShiftPosteService shiftPosteService;

    @GetMapping
    public List<ShiftPoste> getAllShifts() {
        return shiftPosteService.getAllShifts();
    }

    @PostMapping
    public ShiftPoste createShift(@RequestBody ShiftPoste shiftPoste) {
        return shiftPosteService.createShift(shiftPoste);
    }
}
