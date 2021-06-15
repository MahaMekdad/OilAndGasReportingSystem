package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.Concession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyCon extends JpaRepository<Concession, Integer> {
}
