package com.example.ProductManagement.DAO.Repository;


import com.example.ProductManagement.DAO.Entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectsRepositpory extends JpaRepository<Projects,Integer> {
   @Query(value = "SELECT * FROM Projects e where e.fk_empid=:empId",nativeQuery = true)
List<Projects> findBYEmpId(int empId);

}
