package com.example.HopitalPlanningProject.services;

import com.example.HopitalPlanningProject.model.Besoin;
import com.example.HopitalPlanningProject.repositories.BesoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BesoinService {

    @Autowired
    private BesoinRepository repository;

    public List<Besoin> getAll() {
        return repository.findAll();
    }

    public Besoin create(Besoin besoin) {
        return repository.save(besoin);
    }
}
