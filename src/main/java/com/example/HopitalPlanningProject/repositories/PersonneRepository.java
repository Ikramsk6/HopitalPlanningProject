package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité Personne.
 */
@Repository
public interface PersonneRepository extends JpaRepository<Personne, Integer> {
}
