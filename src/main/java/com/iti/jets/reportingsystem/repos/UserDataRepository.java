package com.iti.jets.reportingsystem.repos;

import com.iti.jets.reportingsystem.entities.Userdata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDataRepository extends JpaRepository<Userdata, Integer> {

    List<Userdata> findByUserroles_IdEquals(int userRoleId);

    List<Userdata> findByJobLocationEquals(String jobLocation);

    List<Userdata> findByUserroles_IdEqualsAndJobLocationEquals(int userRoleId, String jobLocation);

    List<Userdata> findAllByUserroles_IdEquals(Integer userRoleId);
}
