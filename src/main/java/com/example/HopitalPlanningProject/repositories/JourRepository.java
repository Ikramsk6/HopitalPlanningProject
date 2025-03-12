package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.Jour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité Jour.
 */
@Repository
public interface JourRepository extends JpaRepository<Jour, String> {
}
