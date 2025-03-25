package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.Besoin;
import com.example.HopitalPlanningProject.model.BesoinId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour gérer les opérations CRUD sur l'entité Besoin.
 */
@Repository
public interface BesoinRepository extends JpaRepository<Besoin, BesoinId> {
}
