package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.models.DrillingInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrillingInfoRepository extends JpaRepository<DrillingInfo, Integer > {
    @Query("select d from DrillingInfo d where d.well.wellId = ?1")
    public DrillingInfo getAllForWellId(int wellId);

    @Query("delete d from DrillingInfo d where d.well.wellId = ?1")
    public DrillingInfo getAllForWellId(int wellId);

}
