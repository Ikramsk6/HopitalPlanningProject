package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.models.Besoin;
import com.example.HopitalPlanningProject.models.Shift;
import com.example.HopitalPlanningProject.repositories.BesoinRepository;
import com.example.HopitalPlanningProject.repositories.ShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BesoinService {
    private final BesoinRepository besoinRepository;
    private final ShiftRepository shiftRepository;

    public BesoinService(BesoinRepository besoinRepository, ShiftRepository shiftRepository) {
        this.besoinRepository = besoinRepository;
        this.shiftRepository = shiftRepository;
    }

    public List<Besoin> getAllBesoins() {
        return besoinRepository.findAll();
    }

    public Optional<Besoin> getBesoinById(Long id) {
        return besoinRepository.findById(id);
    }

    public Besoin createBesoin(Besoin besoin) {
        return besoinRepository.save(besoin);
    }

    public Besoin assignBesoinToShift(Long shiftId, LocalDate date, int nombrePersonnes) {
        Optional<Shift> shiftOpt = shiftRepository.findById(shiftId);
        if (shiftOpt.isEmpty()) {
            throw new RuntimeException("Shift not found");
        }

        Besoin besoin = new Besoin();
        besoin.setShift(shiftOpt.get());
        besoin.setDate(date);
        besoin.getBesoinsParShift().put(shiftId, nombrePersonnes);

        return besoinRepository.save(besoin);
    }

    public void deleteBesoin(Long id) {
        besoinRepository.deleteById(id);
    }
}
