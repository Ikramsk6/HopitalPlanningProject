package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.models.Besoin;
import com.example.HopitalPlanningProject.services.BesoinService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/besoins")
public class BesoinController {
    private final BesoinService besoinService;

    public BesoinController(BesoinService besoinService) {
        this.besoinService = besoinService;
    }

    @GetMapping
    public List<Besoin> getAllBesoins() {
        return besoinService.getAllBesoins();
    }

    @GetMapping("/{id}")
    public Optional<Besoin> getBesoinById(@PathVariable Long id) {
        return besoinService.getBesoinById(id);
    }

    @PostMapping
    public Besoin createBesoin(@RequestBody Besoin besoin) {
        return besoinService.createBesoin(besoin);
    }

    @PostMapping("/assign")
    public Besoin assignBesoin(@RequestParam Long shiftId,
                               @RequestParam LocalDate date,
                               @RequestParam int nombrePersonnes) {
        return besoinService.assignBesoinToShift(shiftId, date, nombrePersonnes);
    }

    @DeleteMapping("/{id}")
    public void deleteBesoin(@PathVariable Long id) {
        besoinService.deleteBesoin(id);
    }
}
