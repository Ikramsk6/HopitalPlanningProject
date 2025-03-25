package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.ShiftPoste;
import com.example.HopitalPlanningProject.repositories.ShiftPosteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftPosteService {

    private final ShiftPosteRepository shiftPosteRepository;

    public ShiftPosteService(ShiftPosteRepository shiftPosteRepository) {
        this.shiftPosteRepository = shiftPosteRepository;
    }

    // Méthode pour récupérer tous les shift postes
    public List<ShiftPoste> getAllShiftPostes() {
        return shiftPosteRepository.findAll();
    }

    // Méthode pour récupérer un shift poste par son ID
    public Optional<ShiftPoste> getShiftPosteById(int id) {
        return shiftPosteRepository.findById(id);
    }

    // Méthode pour enregistrer un shift poste
    public ShiftPoste saveShiftPoste(ShiftPoste shiftPoste) {
        return shiftPosteRepository.save(shiftPoste);
    }

    // Méthode pour supprimer un shift poste
    public void deleteShiftPoste(int id) {
        shiftPosteRepository.deleteById(id);
    }
}
