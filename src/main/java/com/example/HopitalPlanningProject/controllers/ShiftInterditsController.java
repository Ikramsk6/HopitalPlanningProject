package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.models.ShiftInterdits;
import com.example.HopitalPlanningProject.services.ShiftInterditsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shiftsInterdits")
public class ShiftInterditsController {
    private final ShiftInterditsService shiftInterditsService;

    public ShiftInterditsController(ShiftInterditsService shiftInterditsService) {
        this.shiftInterditsService = shiftInterditsService;
    }

    @GetMapping
    public List<ShiftInterdits> getAllShiftInterdits() {
        return shiftInterditsService.getAllShiftInterdits();
    }

    @GetMapping("/{id}")
    public Optional<ShiftInterdits> getShiftInterditsById(@PathVariable Long id) {
        return shiftInterditsService.getShiftInterditsById(id);
    }

    @PostMapping
    public ShiftInterdits saveShiftInterdits(@RequestBody ShiftInterdits shiftInterdits) {
        return shiftInterditsService.saveShiftInterdits(shiftInterdits);
    }

    @DeleteMapping("/{id}")
    public void deleteShiftInterdits(@PathVariable Long id) {
        shiftInterditsService.deleteShiftInterdits(id);
    }
}
