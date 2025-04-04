package com.example.HopitalPlanningProject.controllers;

import com.example.HopitalPlanningProject.model.Personne;
import com.example.HopitalPlanningProject.repositories.ContratRepository;  // Import du repository
import com.example.HopitalPlanningProject.services.PersonneService;
import com.example.HopitalPlanningProject.services.ContratService;  // Ajouter ContratService pour la gestion du contrat
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    private final PersonneService personneService;
    private final ContratRepository contratRepository;  // Déclaration du repository Contrat
    private final ContratService contratService; // Ajout de ContratService pour manipuler les contrats

    // Injection des dépendances
    public PersonneController(PersonneService personneService, ContratRepository contratRepository, ContratService contratService) {
        this.personneService = personneService;
        this.contratRepository = contratRepository;
        this.contratService = contratService;  // Initialisation du ContratService
    }

    @GetMapping
    public List<Personne> getAllPersonnes() {
        return personneService.getAllPersonnes();
    }

    @GetMapping("/{id}")
    public Optional<Personne> getPersonneById(@PathVariable int id) {
        return personneService.getPersonneById(id);
    }

    @PostMapping
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne){
        personneService.savePersonne(personne);

        return ResponseEntity.ok(personne);
    }

    @PutMapping("/{id}")
    public Personne updatePersonne(@PathVariable int id, @RequestBody Personne personne) {
        return personneService.updatePersonne(id, personne);
    }

    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable int id) {
        personneService.deletePersonne(id);
    }

    @DeleteMapping
    public void deleteAllPersonnes() {
        personneService.deleteAllPersonnes();  // Méthode pour supprimer toutes les personnes
    }

}
