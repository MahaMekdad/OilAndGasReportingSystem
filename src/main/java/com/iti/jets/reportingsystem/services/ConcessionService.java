package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.models.ConcessionModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConcessionService {
    //create new concessionModel
    public ConcessionModel addConcession(ConcessionModel concessionModel);

    //get concession using Id
    public ConcessionModel findConcessionById(Long id);

    //get a list of all available concessions
    public List<ConcessionModel> findAllConcessions();

    //update concessionModel
    public ConcessionModel updateConcession(ConcessionModel concessionModel);

    //delete concession using Id
    public void deleteConcession(Long id);

}
