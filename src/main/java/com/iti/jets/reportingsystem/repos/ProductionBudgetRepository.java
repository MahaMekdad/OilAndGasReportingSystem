package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.ProductionBudget;
import com.iti.jets.reportingsystem.models.ProductionBudgetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

public interface ProductionBudgetRepository extends JpaRepository<ProductionBudget,Integer> {
    //get by date
    @Query(value ="from ProductionBudget p where productionDate=?1")
    ProductionBudget findProductionBudgetByProductionDate(String date);
    @Query(value ="from ProductionBudget p where id=?1")
    ProductionBudget findProductionBudgetById(int id);
//todo ana 3aiza a3rf ana el mafrod h update eh bzbt
    //
//    @Transactional
//    @Modifying
//    @Query("update ProductionBudget p set department = ?1 where id =?2")
//    void updateProductionBudget(String dept , int id);
}