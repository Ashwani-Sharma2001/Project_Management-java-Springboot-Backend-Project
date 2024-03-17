package com.example.ProductManagement.DAO.Repository;

import com.example.ProductManagement.DAO.Entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepositpory extends JpaRepository<Projects,Integer> {

}
