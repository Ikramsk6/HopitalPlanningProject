package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoulementGeneratorService {

    private final RoulementService roulementService;
    private final ShiftPosteService shiftPosteService;
    private final PersonneService personneService;
    private final InterdictionPrecedentService interdictionPrecedentService;
    private final SequenceShiftService sequenceShiftService;
    @Autowired
    public RoulementGeneratorService(RoulementService roulementService, ShiftPosteService shiftPosteService,
                                     PersonneService personneService, InterdictionPrecedentService interdictionPrecedentService,
                                     SequenceShiftService sequenceShiftService) {
        this.roulementService = roulementService;
        this.shiftPosteService = shiftPosteService;
        this.personneService = personneService;
        this.interdictionPrecedentService = interdictionPrecedentService;
        this.sequenceShiftService = sequenceShiftService;
    }
    public Roulement generateRoulement() {
        List<ShiftPoste> shifts = shiftPosteService.getAllShifts();
        List<Personne> personnel = personneService.getAllPersonnes();

        // La logique initiale pour créer un roulement vide ou basique
        Roulement roulement = new Roulement();
        // Définir la taille du roulement, etc.
        roulement = roulementService.saveRoulement(roulement);

        // Ajouter des shifts au roulement en respectant les contraintes
        for (ShiftPoste shift : shifts) {
            // Vérifier les interdictions et les préférences avant d'ajouter le shift
            if (checkConstraints(shift, roulement)) {
                SequenceShift sequenceShift = new SequenceShift();
                sequenceShift = sequenceShiftService.createSequence(sequenceShift);
                // Associer la séquence au roulement, etc.
            }
        }

        return roulement;
    }

    private boolean checkConstraints(ShiftPoste shift, Roulement roulement) {
        // Exemple : vérifier les interdictions de séquence de shifts
        List<InterdictionPrecedent> interdictions = interdictionPrecedentService.getAllInterdictions();
        for (InterdictionPrecedent interdiction : interdictions) {
            if (interdiction.getId().getIdShift() == shift.getIdShift()) {
                // Logique pour vérifier si l'interdiction s'applique
            }
        }
        return true;
    }

}
