package com.iti.jets.reportingsystem.repos;

import com.iti.jets.openapi.model.FieldsWellsResponse;
import com.iti.jets.reportingsystem.entities.Field;
import com.iti.jets.reportingsystem.entities.Well;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.iti.jets.reportingsystem.entities.Well;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WellRepository extends JpaRepository<Well, Integer> {

    List<Well> findAllByField_FieldIdEquals(Integer fieldId);

}
