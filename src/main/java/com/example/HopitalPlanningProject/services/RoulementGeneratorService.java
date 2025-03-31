package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Service de génération des roulements hospitaliers.
 * La taille du roulement est saisie en semaines (entre 2 et 12) puis convertie en jours pour la génération.
 * Pour un nombre impair de semaines, on génère initialement sur (weeks + 1) semaines afin d'obtenir une période paire,
 * puis on retire la dernière semaine fictive. Le roulement est constitué en deux phases :
 *   1) La pose des repos (un pattern de 14 jours garantissant 4 repos, avec un weekend sur deux off, est répliqué),
 *   2) L'attribution des shifts sur les jours de travail, avec interdiction de certaines successions.
 */
@Service
public class RoulementGeneratorService {

    private final RoulementService roulementService;
    private final ShiftPosteService shiftPosteService;
    private final InterdictionPrecedentService interdictionPrecedentService;
    private final SequenceShiftService sequenceShiftService;
    private final Random random = new Random();

    @Autowired
    public RoulementGeneratorService(RoulementService roulementService,
                                     ShiftPosteService shiftPosteService,
                                     InterdictionPrecedentService interdictionPrecedentService,
                                     SequenceShiftService sequenceShiftService) {
        this.roulementService = roulementService;
        this.shiftPosteService = shiftPosteService;
        this.interdictionPrecedentService = interdictionPrecedentService;
        this.sequenceShiftService = sequenceShiftService;
    }

    /**
     * Génère un roulement complet.
     * On part du principe que la propriété tailleRoulement de l'objet Roulement contient initialement
     * le nombre de semaines souhaité (entre 2 et 12), puis on convertit cette valeur en jours.
     * Pour un nombre impair de semaines, on ajoute une semaine fictive pour obtenir un nombre pair,
     * puis on la retire à la fin.
     *
     * @return Le roulement généré.
     */
    public Roulement generateRoulement() {
        // Création et enregistrement initial du roulement.
        Roulement roulement = new Roulement();
        roulement = roulementService.saveRoulement(roulement);

        // La taille est saisie en semaines (entre 2 et 12).
        int weeksProvided = roulement.getTailleRoulement();
        if (weeksProvided < 2) {
            weeksProvided = 2;
        } else if (weeksProvided > 12) {
            weeksProvided = 12;
        }
        // Pour faciliter la génération en blocs de 14 jours, si le nombre de semaines est impair,
        // on ajoute une semaine fictive.
        int effectiveWeeks = (weeksProvided % 2 == 1) ? weeksProvided + 1 : weeksProvided;
        int effectiveDays = effectiveWeeks * 7;
        roulement.setTailleRoulement((byte) effectiveDays); // taille temporaire en jours

        // Phase 1 : Génération et placement des repos.
        placerLesRepos(roulement, weeksProvided);

        // Phase 2 : Attribution des shifts sur les jours de travail.
        placerLesShifts(roulement);

        // Évaluation finale (à compléter selon vos règles métier).
        if (!evaluerRoulement(roulement)) {
            roulementService.deleteRoulement(roulement.getIdRoulement());
            throw new RuntimeException("Le roulement ne respecte pas les contraintes finales.");
        }
        return roulement;
    }

    /**
     * Génère le planning de repos pour le roulement et le stocke dans l'objet.
     * Le planning est généré sur une période effective (en jours) calculée sur (weeksProvided + 1) semaines,
     * puis, si le nombre de semaines fourni est impair, la dernière semaine fictive est retirée.
     *
     * @param roulement     Le roulement à compléter.
     * @param weeksProvided Le nombre de semaines saisi initialement.
     */
    private void placerLesRepos(Roulement roulement, int weeksProvided) {
        int totalDays = roulement.getTailleRoulement();

        // Génération du pattern de base sur 14 jours garantissant 4 repos.
        Integer[] basePattern = generateBasePatternForTwoWeeks();

        // Réplication du pattern de base pour couvrir toute la période effective.
        Integer[] effectivePattern = new Integer[totalDays];
        int fullBlocks = totalDays / 14;
        int remainder = totalDays % 14;
        for (int i = 0; i < fullBlocks; i++) {
            System.arraycopy(basePattern, 0, effectivePattern, i * 14, 14);
        }
        if (remainder > 0) {
            System.arraycopy(basePattern, 0, effectivePattern, fullBlocks * 14, remainder);
        }

        // Si le nombre de semaines fourni est impair, on retire la dernière semaine fictive.
        if (weeksProvided % 2 == 1) {
            int finalLength = totalDays - 7;
            effectivePattern = Arrays.copyOf(effectivePattern, finalLength);
            totalDays = finalLength;
        }

        // Vérification : pour chaque bloc complet de 14 jours, il doit y avoir exactement 4 repos.
        for (int block = 0; block < totalDays / 14; block++) {
            int reposCount = 0;
            for (int j = 0; j < 14; j++) {
                if (effectivePattern[block * 14 + j] != null && effectivePattern[block * 14 + j] == -1) {
                    reposCount++;
                }
            }
            if (reposCount != 4) {
                throw new RuntimeException("Erreur: dans le bloc " + (block + 1) +
                        ", 4 repos attendus, trouvés " + reposCount);
            }
        }

        // Vérification : pas plus de 6 jours consécutifs de travail.
        int maxConsecutive = 0, current = 0;
        for (int i = 0; i < totalDays; i++) {
            if (effectivePattern[i] == null) {
                current++;
                maxConsecutive = Math.max(maxConsecutive, current);
            } else {
                current = 0;
            }
        }
        if (maxConsecutive > 6) {
            throw new RuntimeException("Erreur: plus de 6 jours consécutifs de travail (" + maxConsecutive + " jours).");
        }

        // Affichage du planning de repos.
        System.out.println("Planning des repos pour le roulement " + roulement.getIdRoulement()
                + " (" + totalDays + " jours) :");
        for (int i = 0; i < totalDays; i++) {
            String status = (effectivePattern[i] != null && effectivePattern[i] == -1) ? "Repos" : "Vide";
            System.out.println("Jour " + (i + 1) + ": " + status);
        }

        // Stocke le planning dans le roulement.
        roulement.setPlanningRepos(Arrays.asList(effectivePattern));
    }

    /**
     * Génère un pattern de base sur 14 jours garantissant exactement 4 repos.
     * La logique est la suivante :
     * - Choix aléatoire d'un weekend complet à poser en repos (soit jours 6-7, soit jours 13-14),
     * - Placement de deux repos additionnels choisis aléatoirement parmi les jours restants.
     *
     * @return Un tableau de 14 Integer, où -1 représente un repos et null un jour de travail.
     */
    private Integer[] generateBasePatternForTwoWeeks() {
        Integer[] pattern = new Integer[14];
        Arrays.fill(pattern, null);
        int weekendChoisi = random.nextInt(2); // 0 = premier weekend, 1 = deuxième
        int weekendStart, weekendEnd;
        if (weekendChoisi == 0) {
            weekendStart = 5;
            weekendEnd = 6;
        } else {
            weekendStart = 12;
            weekendEnd = 13;
        }
        pattern[weekendStart] = -1;
        pattern[weekendEnd] = -1;

        // Préparer la liste des indices disponibles hors weekend non off.
        List<Integer> availableIndices = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            // Si weekendChoisi est 0, on exclut le weekend 2 (indices 12 et 13)
            // Si weekendChoisi est 1, on exclut le weekend 1 (indices 5 et 6)
            if ((weekendChoisi == 0 && (i == 12 || i == 13)) ||
                    (weekendChoisi == 1 && (i == 5 || i == 6))) {
                continue;
            }
            availableIndices.add(i);
        }
        Collections.shuffle(availableIndices, random);
        pattern[availableIndices.get(0)] = -1;
        pattern[availableIndices.get(1)] = -1;
        return pattern;
    }

    /**
     * Phase 2 : Attribution des shifts sur les jours de travail.
     * Pour chaque jour où le planning est "Vide" (null), on attribue un shift autorisé.
     * La méthode intègre une logique de succession : si le shift du jour précédent est présent,
     * on vérifie dans la liste des interdictions (InterdictionPrecedent) que la paire (shift précédent, shift candidat)
     * n'est pas interdite.
     *
     * @param roulement Le roulement à compléter.
     */
    private void placerLesShifts(Roulement roulement) {
        List<Integer> reposList = roulement.getPlanningRepos();
        if (reposList == null || reposList.isEmpty()) {
            throw new RuntimeException("Le planning de repos n'est pas défini pour ce roulement.");
        }
        Integer[] planning = reposList.toArray(new Integer[0]);
        int totalDays = planning.length;

        List<ShiftPoste> shifts = shiftPosteService.getAllShifts();
        for (int i = 0; i < totalDays; i++) {
            if (planning[i] == null) { // Jour de travail
                List<ShiftPoste> shiftsAutorises = getShiftsAutorisesForDay(i, planning, shifts);
                if (shiftsAutorises.isEmpty()) {
                    throw new RuntimeException("Aucun shift autorisé pour le jour " + (i + 1));
                }
                ShiftPoste shiftChoisi = shiftsAutorises.get(random.nextInt(shiftsAutorises.size()));
                planning[i] = shiftChoisi.getIdShift();

                // Enregistrer la séquence associant ce shift au roulement.
                SequenceShift sequenceShift = new SequenceShift(new SequenceShiftId(roulement.getIdRoulement(), shiftChoisi.getIdShift()));
                sequenceShift.setOrdre(i + 1);
                sequenceShiftService.createSequence(sequenceShift);
            }
        }
        System.out.println("Planning complet du roulement " + roulement.getIdRoulement() + " :");
        for (int i = 0; i < totalDays; i++) {
            String info = (planning[i] != null && planning[i] == -1)
                    ? "Repos" : "Shift ID " + planning[i];
            System.out.println("Jour " + (i + 1) + ": " + info);
        }
        roulement.setPlanningRepos(Arrays.asList(planning));
    }

    /**
     * Filtre les shifts autorisés pour un jour donné en tenant compte des interdictions de succession.
     * Si le jour précédent avait un shift (non repos), alors la paire (shift précédent, shift candidat)
     * est vérifiée dans la liste des interdictions.
     *
     * @param dayIndex L'indice du jour courant.
     * @param planning Le planning actuel (tableau de Integer).
     * @param shifts   La liste complète des shifts disponibles.
     * @return La liste des shifts autorisés pour ce jour.
     */
    private List<ShiftPoste> getShiftsAutorisesForDay(int dayIndex, Integer[] planning, List<ShiftPoste> shifts) {
        List<ShiftPoste> autorises = new ArrayList<>();
        // Récupère la liste des interdictions
        List<InterdictionPrecedent> interdictions = interdictionPrecedentService.getAllInterdictions();
        // Détermine le shift du jour précédent, s'il existe et n'est pas un repos
        Integer previousShiftId = (dayIndex > 0) ? planning[dayIndex - 1] : null;
        for (ShiftPoste candidate : shifts) {
            // Si un shift précédent existe, vérifier la paire interdite
            if (previousShiftId != null && previousShiftId != -1) {
                boolean interdite = interdictions.stream().anyMatch(i ->
                        i.getId().getIdShift() == previousShiftId &&
                                i.getId().getIdShift1() == candidate.getIdShift());
                if (interdite) {
                    continue; // Ce candidat n'est pas autorisé car il est interdit de suivre le shift précédent.
                }
            }
            autorises.add(candidate);
        }
        return autorises;
    }

    /**
     * Évalue la validité finale du roulement selon des critères métier (motifs, succession de shifts, etc.).
     *
     * @param roulement Le roulement à évaluer.
     * @return true si le roulement est valide, false sinon.
     */
    private boolean evaluerRoulement(Roulement roulement) {
        System.out.println("Évaluation finale du roulement " + roulement.getIdRoulement());
        // Ajoutez ici vos règles de validation finale.
        return true;
    }

    /**
     * Génère plusieurs roulements valides, affiche le nombre total obtenu et présente une dizaine d'exemples.
     *
     * @param count Le nombre de roulements souhaité.
     */
    public void generateMultipleRoulements(int count) {
        List<Roulement> validRoulements = new ArrayList<>();
        int attempts = 0;
        while (validRoulements.size() < count && attempts < count * 5) {
            try {
                Roulement r = generateRoulement();
                validRoulements.add(r);
            } catch (Exception e) {
                System.out.println("Échec de génération d'un roulement : " + e.getMessage());
            }
            attempts++;
        }
        if (validRoulements.size() < count) {
            System.out.println("Seuls " + validRoulements.size()
                    + " roulements ont pu être générés sur " + attempts + " tentatives.");
        } else {
            System.out.println(validRoulements.size()
                    + " roulements valides générés sur " + attempts + " tentatives.");
        }
        // Affiche jusqu'à x exemples parmi les roulements valides.
        for (int i = 0; i < Math.min(100, validRoulements.size()); i++) {
            System.out.println("Exemple Roulement " + (i + 1) + " : " + validRoulements.get(i));
        }
    }
}
