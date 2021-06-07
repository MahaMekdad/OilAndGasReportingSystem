package com.iti.jets.reportingsystem.entities;
// Generated Jun 7, 2021, 4:22:33 AM by Hibernate Tools 6.0.0.Alpha2


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Concession generated by hbm2java
 */
@Entity
@Table(name="concession"
    ,catalog="reportingsystem"
)
public class Concession  implements java.io.Serializable {


     private Integer concessionId;
     private String concessionName;
     private Set<Field> fields = new HashSet<Field>(0);

    public Concession() {
    }

	
    public Concession(String concessionName) {
        this.concessionName = concessionName;
    }
    public Concession(String concessionName, Set<Field> fields) {
       this.concessionName = concessionName;
       this.fields = fields;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="concession_id", unique=true, nullable=false)
    public Integer getConcessionId() {
        return this.concessionId;
    }
    
    public void setConcessionId(Integer concessionId) {
        this.concessionId = concessionId;
    }

    
    @Column(name="concession_name", nullable=false, length=50)
    public String getConcessionName() {
        return this.concessionName;
    }
    
    public void setConcessionName(String concessionName) {
        this.concessionName = concessionName;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="concession")
    public Set<Field> getFields() {
        return this.fields;
    }
    
    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }





}


