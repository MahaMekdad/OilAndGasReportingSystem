package com.iti.jets.reportingsystem.controllers;

import com.iti.jets.reportingsystem.models.ConcessionModel;
import com.iti.jets.reportingsystem.services.ConcessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/concessions")
public class ConcessionResource {
    private final ConcessionService concessionService;

    @Autowired
    public ConcessionResource(ConcessionService concessionService) {
        this.concessionService = concessionService;
    }

    @GetMapping
    public ResponseEntity<List<ConcessionModel>> getAllCustomers() {
        List<ConcessionModel> employees = concessionService.findAllConcessions();
        //returning a response entity and passing the object to the body, along with status code ok to give 200 to the user.
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ConcessionModel> addConcession(@RequestBody ConcessionModel concessionModel) {
        concessionService.addConcession(concessionModel);
        return new ResponseEntity<>(concessionModel, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConcessionModel> getConcession(@PathVariable Long id) {
        ConcessionModel concessionModel = concessionService.findConcessionById(id);
        return new ResponseEntity<>(concessionModel, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<ConcessionModel> updateConcession(@RequestBody ConcessionModel concessionModel) {
        ConcessionModel updatedConcession = concessionService.updateConcession(concessionModel);
        return new ResponseEntity<>(updatedConcession, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        concessionService.deleteConcession(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
