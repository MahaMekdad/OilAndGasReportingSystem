package com.iti.jets.reportingsystem.models;

import com.iti.jets.reportingsystem.entities.Field;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

public class ConcessionModel {
    private long id;
    private String concessionName;
    private Set<Field> fields = new HashSet<>();


    public ConcessionModel() {
    }

    public ConcessionModel(String concessionName) {
        this.concessionName = concessionName;
    }

    public ConcessionModel(long id, String concessionName) {
        this.id = id;
        this.concessionName = concessionName;
    }

    public String getConcessionName() {
        return concessionName;
    }

    public void setConcessionName(String concessionName) {
        this.concessionName = concessionName;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }
}
