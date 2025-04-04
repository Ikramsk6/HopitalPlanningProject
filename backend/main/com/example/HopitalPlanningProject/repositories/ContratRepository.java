package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité Contrat.
 */
@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {
    boolean existsByDescriptionContrat(String descriptionContrat);
}

