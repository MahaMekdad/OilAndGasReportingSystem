package com.iti.jets.reportingsystem.services.impls;

import com.iti.jets.reportingsystem.models.Concession;
import com.iti.jets.reportingsystem.repos.ConcessionRepository;
import com.iti.jets.reportingsystem.services.ConcessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcessionServiceImpl implements ConcessionService {

    private final ConcessionRepository concessionRepository;

    @Autowired
    public ConcessionServiceImpl(ConcessionRepository concessionRepository) {
        this.concessionRepository = concessionRepository;
    }

    @Override
    public Concession addConcession(Concession concession) {
        return null;
    }

    @Override
    public Concession findConcessionById(Long id) {
//        return (Concession) concessionRepository.findConcessionById(id).orElseThrow(() -> new EntityNotFoundException(
//                "Concession by id" + id + "Was not found"
//        ));
        return null;
    }

    @Override
    public List<Concession> findAllConcessions() {
        return null;
    }

    @Override
    public Concession updateConcession(Concession concession) {

        return null;
    }

    @Override
    public void deleteConcession(Long id) {
        concessionRepository.deleteById(id);
    }
}
