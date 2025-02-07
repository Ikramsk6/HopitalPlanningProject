package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.models.Besoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BesoinRepository extends JpaRepository<Besoin, Long> {
}
