package com.iti.jets.reportingsystem.repos;


import com.iti.jets.reportingsystem.entities.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FieldRepository extends JpaRepository<Field, Integer> {

}
