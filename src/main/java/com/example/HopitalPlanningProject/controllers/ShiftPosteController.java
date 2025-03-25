package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.ShiftPoste;
import com.example.HopitalPlanningProject.services.ShiftPosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shiftsPostes")
public class ShiftPosteController {

    @Autowired
    private ShiftPosteService shiftPosteService;

    // Récupérer tous les shift postes
    @GetMapping
    public List<ShiftPoste> getAllShifts() {
        return shiftPosteService.getAllShiftPostes();
    }

    // Récupérer un shift poste par son ID
    @GetMapping("/{id}")
    public ResponseEntity<ShiftPoste> getShiftPosteById(@PathVariable int id) {
        Optional<ShiftPoste> shiftPoste = shiftPosteService.getShiftPosteById(id);
        return shiftPoste.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouveau shift poste
    @PostMapping
    public ShiftPoste createShift(@RequestBody ShiftPoste shiftPoste) {
        return shiftPosteService.saveShiftPoste(shiftPoste);
    }

    // Supprimer un shift poste par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShiftPoste(@PathVariable int id) {
        shiftPosteService.deleteShiftPoste(id);
        return ResponseEntity.noContent().build();
    }
}
