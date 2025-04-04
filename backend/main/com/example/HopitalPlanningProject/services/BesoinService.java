package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Besoin;
import com.example.HopitalPlanningProject.model.BesoinId;
import com.example.HopitalPlanningProject.repositories.BesoinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BesoinService {
    private final BesoinRepository besoinRepository;

    public BesoinService(BesoinRepository besoinRepository) {
        this.besoinRepository = besoinRepository;
    }

    public void saveNeeds(Map<String, Integer> needs) {
        List<Besoin> besoins = needs.entrySet().stream()
                .map(entry -> {
                    String[] parts = entry.getKey().split("-");
                    BesoinId id = new BesoinId(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                    return new Besoin(id, entry.getValue().shortValue());
                })
                .collect(Collectors.toList());

        besoinRepository.saveAll(besoins);
    }
}
