package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.BudgetActual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetVsActualRepository extends JpaRepository<BudgetActual,Integer> {
    List<Optional<BudgetActual>> findAllByProductionDate(java.util.Date productionDate);
}
