package com.example.HopitalPlanningProject.controller;

import com.example.HopitalPlanningProject.model.Personne;
import com.example.HopitalPlanningProject.service.PersonneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {

    private final PersonneService personneService;

    public PersonneController(PersonneService personneService) {
        this.personneService = personneService;
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
    public Personne createPersonne(@RequestBody Personne personne) {
        return personneService.createPersonne(personne);
    }

    @PutMapping("/{id}")
    public Personne updatePersonne(@PathVariable int id, @RequestBody Personne personne) {
        return personneService.updatePersonne(id, personne);
    }

    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable int id) {
        personneService.deletePersonne(id);
    }
}
