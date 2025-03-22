package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.Arrays;

/**
 * Service principal pour la génération de roulements hospitaliers.
 * Il se décompose en deux phases :
 * 1) La pose des repos.
 * 2) L'assignation des shifts sur les jours travaillés.
 */
@Service
public class RoulementGeneratorService {

    private final RoulementService roulementService;
    private final ShiftPosteService shiftPosteService;
    private final InterdictionPrecedentService interdictionPrecedentService;
    private final SequenceShiftService sequenceShiftService;

    /**
     * Constructeur pour l'injection par constructeur des services nécessaires.
     */
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
     * Méthode principale de génération d'un roulement complet.
     * @return Le roulement généré et enregistré en base.
     * @throws RuntimeException si le roulement ne satisfait pas les contraintes imposées.
     */
    public Roulement generateRoulement() {
        // 1. Création initiale du roulement et enregistrement en base.
        Roulement roulement = new Roulement();
        roulement = roulementService.saveRoulement(roulement);

        // 2. Phase 1 : Poser les repos (sur 14 jours minimum).
        placerLesRepos(roulement);

        // 3. Phase 2 : Poser les shifts (uniquement sur les jours de travail).
        placerLesShifts(roulement);

        // 4. Évaluation finale du roulement.
        if (!evaluerRoulement(roulement)) {
            // En cas d'échec, on supprime le roulement et on lève une exception.
            roulementService.deleteRoulement(roulement.getIdRoulement());
            throw new RuntimeException("Le roulement ne respecte pas les motifs ou contraintes finales.");
        }

        return roulement;
    }

    /**
     * Phase 1 : Pose des repos sur le roulement.
     * - Assure 4 repos sur chaque fenêtre de 14 jours.
     * - Assure un maximum de 6 jours de travail consécutifs.
     * @param roulement L'objet Roulement dans lequel on stocke le planning de repos.
     */
    private void placerLesRepos(Roulement roulement) {
        // Détermine la taille du roulement (min 14 jours).
        int totalDays = roulement.getTailleRoulement();
        if (totalDays < 14) {
            totalDays = 14;
            roulement.setTailleRoulement((byte) totalDays);
        }

        // Génère un pattern de base sur 14 jours (4 repos, max 6 jours consécutifs de travail).
        Integer[] basePattern = generateBasePatternForTwoWeeks();

        // Recopie le pattern pour couvrir toute la durée du roulement.
        Integer[] finalPattern = new Integer[totalDays];
        int fullBlocks = totalDays / 14;
        int remainder = totalDays % 14;

        // Copie bloc par bloc (14 jours).
        for (int i = 0; i < fullBlocks; i++) {
            System.arraycopy(basePattern, 0, finalPattern, i * 14, 14);
        }
        // Copie le reste s'il y a un reliquat.
        if (remainder > 0) {
            System.arraycopy(basePattern, 0, finalPattern, fullBlocks * 14, remainder);
        }

        // Vérification : chaque fenêtre glissante de 14 jours doit avoir exactement 4 repos.
        for (int start = 0; start < totalDays; start++) {
            int reposCount = 0;
            for (int j = 0; j < 14; j++) {
                int index = start + j;
                Integer val;
                if (index < totalDays) {
                    val = finalPattern[index];
                } else {
                    // Si on dépasse, on complète avec le début du pattern de base.
                    val = basePattern[index - totalDays];
                }
                if (val != null && val == -1) {
                    reposCount++;
                }
            }
            if (reposCount != 4) {
                throw new RuntimeException("La contrainte de 4 repos sur 14 jours n'est pas respectée "
                        + "à partir du jour " + (start + 1) + " (repos = " + reposCount + ").");
            }
        }

        // Vérification : pas plus de 6 jours consécutifs de travail.
        int maxConsecutive = 0, current = 0;
        for (int i = 0; i < totalDays; i++) {
            if (finalPattern[i] == null) { // Jour travaillé
                current++;
                maxConsecutive = Math.max(maxConsecutive, current);
            } else {
                current = 0;
            }
        }
        if (maxConsecutive > 6) {
            throw new RuntimeException("La contrainte de 6 jours consécutifs max n'est pas respectée (observé : "
                    + maxConsecutive + ").");
        }

        // Affichage pour vérification
        System.out.println("Planning des repos (Roulement " + roulement.getIdRoulement() + ", " + totalDays + " jours) :");
        for (int i = 0; i < totalDays; i++) {
            String status = (finalPattern[i] != null && finalPattern[i] == -1) ? "Repos" : "Vide";
            System.out.println("Jour " + (i + 1) + ": " + status);
        }

        // Stockage du planning de repos dans le roulement (sous forme de List<Integer>).
        roulement.setPlanningRepos(Arrays.asList(finalPattern));
    }

    /**
     * Génère un pattern de 14 jours contenant exactement 4 repos :
     *  - Choix aléatoire d'un weekend complet (samedi/dimanche) comme repos.
     *  - Ajout de 2 repos additionnels aléatoires en semaine.
     *  - Les jours travaillés sont marqués par null, les repos par -1.
     * @return Un tableau de 14 jours, chaque case étant soit null (travail) soit -1 (repos).
     */
    private Integer[] generateBasePatternForTwoWeeks() {
        Integer[] pattern = new Integer[14];
        // Initialise tous les jours à null (travail).
        for (int i = 0; i < 14; i++) {
            pattern[i] = null;
        }

        Random random = new Random();
        int weekendChoisi = random.nextInt(2); // 0 = premier weekend, 1 = deuxième weekend

        // Marque le weekend choisi en repos
        if (weekendChoisi == 0) {
            // Indices 5 et 6 (jours 6 et 7) : repos
            pattern[5] = -1;
            pattern[6] = -1;
        } else {
            // Indices 12 et 13 (jours 13 et 14) : repos
            pattern[12] = -1;
            pattern[13] = -1;
        }

        // Détermine les indices disponibles pour placer 2 repos supplémentaires.
        int[] availableIndices = (weekendChoisi == 0)
                ? new int[] {0, 1, 2, 3, 4, 7, 8, 9, 10, 11}
                : new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        // Place 2 repos additionnels de manière aléatoire.
        int index1 = availableIndices[random.nextInt(availableIndices.length)];
        int index2 = availableIndices[random.nextInt(availableIndices.length)];
        while (index2 == index1) {
            index2 = availableIndices[random.nextInt(availableIndices.length)];
        }
        pattern[index1] = -1;
        pattern[index2] = -1;

        return pattern;
    }

    /**
     * Phase 2 : Assignation des shifts sur les jours de travail (cases null).
     * @param roulement Le roulement sur lequel on place les shifts.
     * @throws RuntimeException si aucun shift n'est autorisé pour un jour donné.
     */
    private void placerLesShifts(Roulement roulement) {
        // Récupère le planning de repos (cases = -1) et jours vides (cases = null).
        List<Integer> reposList = roulement.getPlanningRepos();
        if (reposList == null || reposList.isEmpty()) {
            throw new RuntimeException("Aucun planning de repos n'est défini dans ce roulement.");
        }
        Integer[] planning = reposList.toArray(new Integer[0]);

        int totalDays = roulement.getTailleRoulement();

        // Récupère tous les shifts disponibles
        List<ShiftPoste> shifts = shiftPosteService.getAllShifts();

        // Parcourt chaque jour pour y assigner un shift si c'est un jour de travail
        for (int i = 0; i < totalDays; i++) {
            if (planning[i] == null) {  // Jour de travail
                // Filtre les shifts autorisés pour ce jour
                List<ShiftPoste> shiftsAutorises = getShiftsAutorisesForDay(i, planning, shifts);

                if (shiftsAutorises.isEmpty()) {
                    throw new RuntimeException("Aucun shift autorisé pour le jour " + (i + 1));
                }

                // Choisit aléatoirement un shift parmi les autorisés
                ShiftPoste shiftChoisi = shiftsAutorises.get(new Random().nextInt(shiftsAutorises.size()));

                // Remplace null par l'ID du shift
                planning[i] = shiftChoisi.getIdShift();

                // Optionnel : crée et enregistre l'entité SequenceShift (association jour/shift)
                SequenceShift sequenceShift =
                        new SequenceShift(new SequenceShiftId(roulement.getIdRoulement(), shiftChoisi.getIdShift()));
                sequenceShift.setOrdre(i + 1); // Ordre = numéro du jour
                sequenceShiftService.createSequence(sequenceShift);
            }
        }

        // Affichage final pour vérification
        System.out.println("Planning complet du roulement " + roulement.getIdRoulement() + " :");
        for (int i = 0; i < totalDays; i++) {
            // -1 = repos, sinon ID du shift
            String dayInfo = (planning[i] != null && planning[i] == -1)
                    ? "Repos"
                    : "Shift ID " + planning[i];
            System.out.println("Jour " + (i + 1) + ": " + dayInfo);
        }
        // Possibilité de mettre à jour roulement.setPlanningRepos(Arrays.asList(planning)) si on veut sauvegarder le planning final.
    }

    /**
     * Filtre les shifts autorisés pour un jour donné.
     * @param dayIndex Index du jour (0-based).
     * @param planning Tableau contenant -1 pour repos, null pour travail non assigné, ou l'ID d'un shift.
     * @param shifts La liste de tous les shifts disponibles.
     * @return La liste des shifts autorisés (sans contrainte supplémentaire, on renvoie tous les shifts).
     */
    private List<ShiftPoste> getShiftsAutorisesForDay(int dayIndex, Integer[] planning, List<ShiftPoste> shifts) {
        // Ici, tu peux appliquer des règles métier plus complexes (interdictions, successions, préférences...).
        // Pour l'instant, on renvoie simplement tous les shifts.
        return shifts;
    }

    /**
     * Évalue la validité finale du roulement (après la pose des repos et des shifts).
     * @param roulement Le roulement à évaluer.
     * @return true si le roulement est valide, false sinon.
     */
    private boolean evaluerRoulement(Roulement roulement) {
        System.out.println("Évaluation finale du roulement " + roulement.getIdRoulement());
        // Implémentez ici la logique finale (ex. motifs, successions de shifts, etc.).
        return true;
    }
}
