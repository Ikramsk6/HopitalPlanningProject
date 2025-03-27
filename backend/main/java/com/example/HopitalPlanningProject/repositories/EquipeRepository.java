package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité Equipe.
 */
@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
}
