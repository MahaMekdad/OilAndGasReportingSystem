package com.iti.jets.reportingsystem.services;
import com.iti.jets.openapi.model.ConcessionRequest;
import com.iti.jets.openapi.model.ConcessionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConcessionService {
    //create new concessionModel
    public ConcessionResponse addConcession(ConcessionRequest concessionRequest);

    //get concession using Id
    public ConcessionResponse findConcessionById(int id);

    //get a list of all available concessions
    public List<ConcessionResponse> findAllConcessions();

    //update concessionModel
    public ConcessionResponse updateConcession(int concessionId,ConcessionRequest concessionRequest);

    //delete concession using Id
    public void deleteConcession(int id);

}
