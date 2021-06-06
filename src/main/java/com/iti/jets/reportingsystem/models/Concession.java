package com.iti.jets.reportingsystem.models;

import com.iti.jets.reportingsystem.entities.Field;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

public class Concession {
    private long id;
    private String concessionName;
    private Set<Field> fields = new HashSet<>();


    public Concession() {
    }

    public Concession(String concessionName) {
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
