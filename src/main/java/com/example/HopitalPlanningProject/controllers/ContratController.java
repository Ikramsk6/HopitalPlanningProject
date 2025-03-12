package com.example.HopitalPlanningProject.controller;

import com.example.HopitalPlanningProject.model.Contrat;
import com.example.HopitalPlanningProject.service.ContratService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {

    private final ContratService contratService;

    public ContratController(ContratService contratService) {
        this.contratService = contratService;
    }

    @GetMapping
    public List<Contrat> getAllContrats() {
        return contratService.getAllContrats();
    }

    @GetMapping("/{id}")
    public Optional<Contrat> getContratById(@PathVariable int id) {
        return contratService.getContratById(id);
    }

    @PostMapping
    public Contrat createContrat(@RequestBody Contrat contrat) {
        return contratService.createContrat(contrat);
    }

    @PutMapping("/{id}")
    public Contrat updateContrat(@PathVariable int id, @RequestBody Contrat contrat) {
        return contratService.updateContrat(id, contrat);
    }

    @DeleteMapping("/{id}")
    public void deleteContrat(@PathVariable int id) {
        contratService.deleteContrat(id);
    }
}
