package com.iti.jets.reportingsystem.services;

import com.iti.jets.reportingsystem.models.Concession;
import com.iti.jets.reportingsystem.repos.ConcessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConcessionService {
    //create new concession
    public Concession addConcession(Concession concession);

    //get concession using Id
    public Concession findConcessionById(Long id);

    //get a list of all available concessions
    public List<Concession> findAllConcessions();

    //update concession
    public Concession updateConcession(Concession concession);

    //delete concession using Id
    public void deleteConcession(Long id);

}
