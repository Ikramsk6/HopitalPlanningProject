package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.models.ShiftInterdits;
import com.example.HopitalPlanningProject.services.ShiftInterditsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shifts-interdits")
public class ShiftInterditsController {
    private final ShiftInterditsService shiftInterditsService;

    public ShiftInterditsController(ShiftInterditsService shiftInterditsService) {
        this.shiftInterditsService = shiftInterditsService;
    }

    @GetMapping
    public List<ShiftInterdits> getAllShiftsInterdits() {
        return shiftInterditsService.getAllShiftsInterdits();
    }

    @GetMapping("/{id}")
    public Optional<ShiftInterdits> getShiftInterditsById(@PathVariable Long id) {
        return shiftInterditsService.getShiftInterditsById(id);
    }

    @PostMapping
    public ShiftInterdits createShiftInterdits(@RequestBody ShiftInterdits shiftInterdits) {
        return shiftInterditsService.createShiftInterdits(shiftInterdits);
    }

    @DeleteMapping("/{id}")
    public void deleteShiftInterdits(@PathVariable Long id) {
        shiftInterditsService.deleteShiftInterdits(id);
    }
}
