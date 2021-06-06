package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.Concession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConcessionRepository extends JpaRepository<Concession, Long> {
    Optional<Concession> findConcessionById(Long id);
}
