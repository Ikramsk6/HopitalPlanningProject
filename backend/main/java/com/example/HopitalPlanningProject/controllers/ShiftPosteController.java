package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.ShiftPoste;
import com.example.HopitalPlanningProject.services.ShiftPosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001")
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

    // Ajout de la méthode DELETE pour supprimer tous les shifts
    @DeleteMapping
    public void deleteAllShifts() {
        try {
            shiftPosteService.deleteAllShifts();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de tous les shifts : " + e.getMessage());
        }
    }

    // Nouvelle méthode pour supprimer un shift par son ID
    @DeleteMapping("/{id}")
    public void deleteShift(@PathVariable int id) {
        try {
            shiftPosteService.deleteShift(id);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression du shift avec l'ID " + id + " : " + e.getMessage());
        }
    }
}
