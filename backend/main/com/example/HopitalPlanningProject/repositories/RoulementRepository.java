package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.Roulement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité Roulement.
 */
@Repository
public interface RoulementRepository extends JpaRepository<Roulement, Integer> {
}
