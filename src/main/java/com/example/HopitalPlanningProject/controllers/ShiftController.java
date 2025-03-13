package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.Shift;
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
    public Optional<Shift> getShiftById(@PathVariable int id) {
        return shiftService.getShiftById(id);
    }

    // @PostMapping
    // public Shift createShift(@RequestBody Shift shift) {
    //     // return shiftService.createShift(shift);
    //     // methode n existe pas dans le service
    // }

    @DeleteMapping("/{id}")
    public void deleteShift(@PathVariable int id) {
        shiftService.deleteShift(id);
    }
}
