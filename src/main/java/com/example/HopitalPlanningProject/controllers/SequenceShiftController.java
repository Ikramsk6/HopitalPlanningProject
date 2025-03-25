package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.SequenceShift;
import com.example.HopitalPlanningProject.model.SequenceShiftId;
import com.example.HopitalPlanningProject.services.SequenceShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sequenceShifts")
public class SequenceShiftController {

    @Autowired
    private SequenceShiftService sequenceShiftService;

    // Récupérer tous les SequenceShifts
    @GetMapping
    public List<SequenceShift> getAllSequences() {
        return sequenceShiftService.getAllSequenceShifts();
    }

    // Créer un SequenceShift
    @PostMapping
    public SequenceShift createSequence(@RequestBody SequenceShift sequenceShift) {
        return sequenceShiftService.saveSequenceShift(sequenceShift);
    }

    // Récupérer un SequenceShift par son ID composite
    @GetMapping("/{idRoulement}/{idShift}")
    public Optional<SequenceShift> getSequenceById(@PathVariable int idRoulement, @PathVariable int idShift) {
        SequenceShiftId id = new SequenceShiftId(idRoulement, idShift);
        return sequenceShiftService.getSequenceShiftById(id);
    }

    // Supprimer un SequenceShift par son ID composite
    @DeleteMapping("/{idRoulement}/{idShift}")
    public void deleteSequence(@PathVariable int idRoulement, @PathVariable int idShift) {
        SequenceShiftId id = new SequenceShiftId(idRoulement, idShift);
        sequenceShiftService.deleteSequenceShift(id);
    }
}
