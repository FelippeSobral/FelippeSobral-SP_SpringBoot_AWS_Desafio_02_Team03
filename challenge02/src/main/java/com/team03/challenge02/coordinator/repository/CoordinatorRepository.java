package com.team03.challenge02.coordinator.repository;

import com.team03.challenge02.coordinator.entity.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {
}
