package com.iti.jets.reportingsystem.repos;


import com.iti.jets.openapi.model.ConcessionsFieldsResponse;
import com.iti.jets.reportingsystem.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FieldRepository extends JpaRepository<Field, Integer> {

    List<Field> findAllByConcession_ConcessionIdEquals(Integer concessionId);

}


