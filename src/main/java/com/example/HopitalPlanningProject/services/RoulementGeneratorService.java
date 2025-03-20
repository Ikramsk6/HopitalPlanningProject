package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RoulementGeneratorService {

    private final RoulementService roulementService;
    private final ShiftPosteService shiftPosteService;
    private final PersonneService personneService;
    private final InterdictionPrecedentService interdictionPrecedentService;
    private final SequenceShiftService sequenceShiftService;

    @Autowired
    public RoulementGeneratorService(RoulementService roulementService,
                                     ShiftPosteService shiftPosteService,
                                     PersonneService personneService,
                                     InterdictionPrecedentService interdictionPrecedentService,
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

        // Création initiale du roulement (tu peux initialiser la taille si nécessaire)
        Roulement roulement = new Roulement();
        // Par exemple, définir une taille de roulement (en jours)
        // roulement.setTailleRoulement((byte) 14);
        roulement = roulementService.saveRoulement(roulement);

        // Phase 1 : Placer les repos
        placerLesRepos(roulement);

        // Phase 2 : Placer les shifts
        placerLesShifts(roulement);

        // Évaluation finale du roulement selon les motifs et contraintes
        if (!evaluerRoulement(roulement)) {
            // En cas d'échec, supprimer le roulement et éventuellement lever une exception ou réessayer
            roulementService.deleteRoulement(roulement.getIdRoulement());
            throw new RuntimeException("Le roulement ne respecte pas les motifs requis.");
        }

        return roulement;
    }

    private void placerLesRepos(Roulement roulement) {
        // Implémente ici la logique pour placer les repos
        // Par exemple, fixer 4 repos sur 2 semaines avec au moins 2 consécutifs incluant un dimanche
        // Cette méthode sera à adapter selon tes besoins métier.
        System.out.println("Placer les repos pour le roulement " + roulement.getIdRoulement());
    }

    private void placerLesShifts(Roulement roulement) {
        List<ShiftPoste> shifts = shiftPosteService.getAllShifts();
        Random random = new Random();

        // Exemple d'assignation : on parcourt tous les shifts et on décide aléatoirement de les placer
        for (ShiftPoste shift : shifts) {
            // Simulation d'une décision : on tente de placer le shift
            if (random.nextBoolean()) {
                if (checkConstraints(shift, roulement)) {
                    // Utilisation de getIdRoulement() pour obtenir l'identifiant généré
                    SequenceShift sequenceShift = new SequenceShift(new SequenceShiftId(roulement.getIdRoulement(), shift.getIdShift()));
                    sequenceShiftService.createSequence(sequenceShift);
                }
            }
        }
    }

    private boolean checkConstraints(ShiftPoste shift, Roulement roulement) {
        // Exemple de vérification des contraintes d'interdiction sur le shift
        List<InterdictionPrecedent> interdictions = interdictionPrecedentService.getAllInterdictions();
        for (InterdictionPrecedent interdiction : interdictions) {
            // Vérifie si le shift est interdit (adaptation selon ta logique)
            if (interdiction.getId().getIdShift() == shift.getIdShift()) {
                System.out.println("Shift " + shift.getIdShift() + " interdit pour le roulement " + roulement.getIdRoulement());
                return false;
            }
        }
        return true;
    }

    private boolean evaluerRoulement(Roulement roulement) {
        // Implémente ici la logique finale pour évaluer si le roulement respecte les motifs et contraintes (succession, fréquence weekend, etc.)
        // Retourne false si le roulement ne respecte pas les contraintes.
        System.out.println("Évaluation finale du roulement " + roulement.getIdRoulement());
        return true;
    }
}
