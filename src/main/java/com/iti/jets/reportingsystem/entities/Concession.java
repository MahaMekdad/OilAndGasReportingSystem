package com.iti.jets.reportingsystem.entities;
// Generated Jun 7, 2021, 4:22:33 AM by Hibernate Tools 6.0.0.Alpha2


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Concession generated by hbm2java
 */
@Entity
@Table(name = "concession"
        , catalog = "reportingsystem"
)
@Access(AccessType.PROPERTY)
public class Concession implements java.io.Serializable {


    private Integer concessionId;
    private String concessionName;
    private Set<Field> fields = new HashSet<Field>(0);

    public Concession() {
    }

    public Concession(String concessionName) {
        this.concessionName = concessionName;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "concession_id", unique = true, nullable = false)
    public Integer getConcessionId() {
        return this.concessionId;
    }

    public void setConcessionId(Integer concessionId) {
        this.concessionId = concessionId;
    }

    @Column(name = "concession_name", nullable = false, length = 50)
    public String getConcessionName() {
        return this.concessionName;
    }

    public void setConcessionName(String concessionName) {
        this.concessionName = concessionName;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "concession")
    @JsonManagedReference
    public Set<Field> getFields() {
        return this.fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }


}


