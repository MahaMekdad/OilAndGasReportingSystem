package com.iti.jets.reportingsystem.entities;
// Generated Jun 7, 2021, 4:22:33 AM by Hibernate Tools 6.0.0.Alpha2


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Field generated by hbm2java
 */
@Entity
@Table(name="field"
    ,catalog="reportingsystem"
    , uniqueConstraints = {@UniqueConstraint(columnNames="field_code"), @UniqueConstraint(columnNames="field_name")} 
)
public class Field  implements java.io.Serializable {


     private Integer fieldId;
     private Concession concession;
     private String fieldName;
     private String fieldCode;
     private Set<Well> wells = new HashSet<Well>(0);

    public Field() {
    }

	
    public Field(Concession concession, String fieldName, String fieldCode) {
        this.concession = concession;
        this.fieldName = fieldName;
        this.fieldCode = fieldCode;
    }
    public Field(Concession concession, String fieldName, String fieldCode, Set<Well> wells) {
       this.concession = concession;
       this.fieldName = fieldName;
       this.fieldCode = fieldCode;
       this.wells = wells;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="field_id", unique=true, nullable=false)
    public Integer getFieldId() {
        return this.fieldId;
    }
    
    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="concession_id", nullable=false)
    public Concession getConcession() {
        return this.concession;
    }
    
    public void setConcession(Concession concession) {
        this.concession = concession;
    }

    
    @Column(name="field_name", unique=true, nullable=false, length=80)
    public String getFieldName() {
        return this.fieldName;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    
    @Column(name="field_code", unique=true, nullable=false, length=45)
    public String getFieldCode() {
        return this.fieldCode;
    }
    
    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="field")
    public Set<Well> getWells() {
        return this.wells;
    }
    
    public void setWells(Set<Well> wells) {
        this.wells = wells;
    }




}


