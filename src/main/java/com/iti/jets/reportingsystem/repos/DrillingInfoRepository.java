package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.DrillingInfo;
import com.iti.jets.reportingsystem.entities.Well;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface DrillingInfoRepository extends JpaRepository<DrillingInfo, Integer > {
    @Query("select d from DrillingInfo d where d.well.wellId = ?1")
    public List<DrillingInfo> getAllForWellId(int wellId);

    public List<DrillingInfo> findAllByWell_WellIdEquals(int wellId);

//    @Transactional
//    @Modifying
//    @Query("update DrillingInfo d set releaseDate = ?1 ,wellDescription= ?2 ,wellType= ?3 , boreType= ?4 , measuredDepth= ?5 ,tvdDepth= ?6 , bbtp=?7 ,productionGeneralInfo=?8 where wellId =?9")
//    void updateWellDrillingInfo(String date , String wellDesc , String wellType , String boreType , int mesDepth , int tvDepth , int bbtp ,String prodGenInfo ,int wellId);

    public DrillingInfo findAllByWell_WellIdEqualsAndIdEquals(int wellId, int id);



}
