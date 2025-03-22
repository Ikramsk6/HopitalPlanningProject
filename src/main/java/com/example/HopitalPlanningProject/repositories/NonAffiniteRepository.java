package com.example.HopitalPlanningProject.repositories;

import com.example.HopitalPlanningProject.model.NonAffinite;
import com.example.HopitalPlanningProject.model.NonAffiniteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonAffiniteRepository extends JpaRepository<NonAffinite, NonAffiniteId> {
}
