package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.Besoin;
import com.example.HopitalPlanningProject.model.BesoinId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BesoinRepository extends JpaRepository<Besoin, BesoinId> {
}
