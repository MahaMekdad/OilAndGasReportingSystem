package com.iti.jets.reportingsystem.entities;
// Generated Jun 2, 2021, 9:20:41 PM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * ShutinTypeLevel3 generated by hbm2java
 */
@Entity
@Table(name="shutin_type_level3"
    ,catalog="reportingsysschema"
)
public class ShutinTypeLevel3  implements java.io.Serializable {


     private int id;
     private ShutinTypeLevel2 shutinTypeLevel2;
     private String description;
     private Set<ShutinTypeLevel4> shutinTypeLevel4s = new HashSet<ShutinTypeLevel4>(0);

    public ShutinTypeLevel3() {
    }

	
    public ShutinTypeLevel3(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public ShutinTypeLevel3(int id, ShutinTypeLevel2 shutinTypeLevel2, String description, Set<ShutinTypeLevel4> shutinTypeLevel4s) {
       this.id = id;
       this.shutinTypeLevel2 = shutinTypeLevel2;
       this.description = description;
       this.shutinTypeLevel4s = shutinTypeLevel4s;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sh_id_level2")
    public ShutinTypeLevel2 getShutinTypeLevel2() {
        return this.shutinTypeLevel2;
    }
    
    public void setShutinTypeLevel2(ShutinTypeLevel2 shutinTypeLevel2) {
        this.shutinTypeLevel2 = shutinTypeLevel2;
    }

    
    @Column(name="description", nullable=false, length=45)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="shutinTypeLevel3")
    public Set<ShutinTypeLevel4> getShutinTypeLevel4s() {
        return this.shutinTypeLevel4s;
    }
    
    public void setShutinTypeLevel4s(Set<ShutinTypeLevel4> shutinTypeLevel4s) {
        this.shutinTypeLevel4s = shutinTypeLevel4s;
    }




}


