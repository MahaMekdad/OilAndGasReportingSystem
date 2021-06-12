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
 * Userroles generated by hbm2java
 */
@Entity
@Table(name="userroles"
    ,catalog="reportingsystem"
)
public class Userroles  implements java.io.Serializable {


     private Integer id;
     private String role;
     private Set<Userdata> userdatas = new HashSet<Userdata>(0);

    public Userroles() {
    }

    public Userroles(String role, Set<Userdata> userdatas) {
       this.role = role;
       this.userdatas = userdatas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="role", length=100)
    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="userroles")
    public Set<Userdata> getUserdatas() {
        return this.userdatas;
    }
    
    public void setUserdatas(Set<Userdata> userdatas) {
        this.userdatas = userdatas;
    }




}


