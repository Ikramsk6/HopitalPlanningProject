package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.models.Roulement;
import com.example.HopitalPlanningProject.models.Shift;
import com.example.HopitalPlanningProject.services.RoulementService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/roulements")
public class RoulementController {
    private final RoulementService roulementService;

    public RoulementController(RoulementService roulementService) {
        this.roulementService = roulementService;
    }
    // parametre here is the roulement id . and it return a json contenant nbmaxroulement et taillemax roulement
    // taille roulement est en semaines
    // pour raphael il suffit seulement de faire un GET /api/roulements/{id}/infos
    @GetMapping("/{id}/infos")
    public ResponseEntity<Map<String, Integer>> getGeneralInfo(@PathVariable Long id) {
        Optional<Roulement> optRoulement = roulementService.getRoulementById(id);
        if (optRoulement.isPresent()) {
            Roulement r = optRoulement.get();
            Map<String, Integer> infos = new HashMap<>();
            infos.put("nbMaxRoulement", r.getNbMaxRoulement());
            infos.put("nbMaxTailleRoulement", r.getNbMaxTailleRoulement());
            return ResponseEntity.ok(infos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @GetMapping
    public List<Roulement> getAllRoulements() {
        return roulementService.getAllRoulements();
    }

    @GetMapping("/{id}")
    public Optional<Roulement> getRoulementById(@PathVariable Long id) {
        return roulementService.getRoulementById(id);
    }

    @PostMapping
    public Roulement saveRoulement(@RequestBody Roulement roulement) {
        return roulementService.saveRoulement(roulement);
    }

    @DeleteMapping("/{id}")
    public void deleteRoulement(@PathVariable Long id) {
        roulementService.deleteRoulement(id);
    }

    @PostMapping("/{roulementId}/ajouterShift")
    public void ajouterShiftAuRoulement(@PathVariable Long roulementId, @RequestBody Shift shift) {
        roulementService.ajouterShiftAuRoulement(roulementId, shift);
    }
}
