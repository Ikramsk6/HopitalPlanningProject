package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.models.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
}
