package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.models.Shift;
import com.example.HopitalPlanningProject.services.ShiftService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {
    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping
    public List<Shift> getAllShifts() {
        return shiftService.getAllShifts();
    }

    @GetMapping("/{id}")
    public Optional<Shift> getShiftById(@PathVariable Long id) {
        return shiftService.getShiftById(id);
    }

    @PostMapping("/add")
    public Shift addShift(@RequestParam String nom, @RequestParam String type) {
        Shift shift = new Shift();
        shift.setNom(nom);
        shift.setType(type);
        return shiftService.createShift(shift);
    }

    @PutMapping("/{id}")
    public Shift updateShift(@PathVariable Long id, @RequestBody Shift shiftDetails) {
        return shiftService.updateShift(id, shiftDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteShift(@PathVariable Long id) {
        shiftService.deleteShift(id);
    }
}
