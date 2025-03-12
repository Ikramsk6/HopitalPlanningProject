package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.Shift;
import com.example.HopitalPlanningProject.repositories.ShiftRepository;
import org.springframework.stereotype.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Optional<Shift> getShiftById(Long id) {
        return shiftRepository.findById(id);
    }

    public Shift createShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    public Shift updateShift(Long id, Shift shiftDetails) {
        return shiftRepository.findById(id).map(shift -> {
            shift.setNom(shiftDetails.getNom());
            shift.setType(shiftDetails.getType());
            return shiftRepository.save(shift);
        }).orElse(null);
    }

    public void deleteShift(Long id) {
        shiftRepository.deleteById(id);
    }
    public void ajouterShiftsDepuisConsole() {
        Scanner scanner = new Scanner(System.in);
        List<String> typesValides = Arrays.asList("Travail", "RTT", "Repos", "Formation");

        while (true) {
            System.out.println("Entrez le nom du shift (ex: Matin, Soir, Aprem) ou tapez 'exit' pour quitter :");
            String nomShift = scanner.nextLine().trim();
            if (nomShift.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.println("Entrez le type du shift (Travail, RTT, Repos, Formation) :");
            String typeShift = scanner.nextLine().trim();

            if (!typesValides.contains(typeShift)) {
                System.out.println("Type invalide. Choisissez parmi : " + typesValides);
                continue;
            }

            Shift shift = new Shift();
            shift.setNom(nomShift);
            shift.setType(typeShift);

            shiftRepository.save(shift);
            System.out.println("Shift ajouté avec succès !");
        }

        System.out.println("Fin de la saisie des shifts.");
    }

}
