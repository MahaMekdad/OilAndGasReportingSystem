package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.models.ProductionBudgetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionBudgetRepository extends JpaRepository<ProductionBudgetModel ,Integer> {
}
