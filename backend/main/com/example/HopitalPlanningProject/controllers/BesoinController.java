package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.services.BesoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Ajuste selon ton front-end
public class BesoinController {
    private final BesoinService besoinService;

    public BesoinController(BesoinService besoinService) {
        this.besoinService = besoinService;
    }

    @PostMapping("/submitNeeds")
    public ResponseEntity<String> submitNeeds(@RequestBody Map<String, Integer> needs) {
        besoinService.saveNeeds(needs);
        return ResponseEntity.ok("Besoins enregistrés avec succès !");
    }
}
