package com.example.ProductManagement.DAO.Repository;

import com.example.ProductManagement.DAO.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query("SELECT e FROM Employee e WHERE lower(e.EmployeeName) LIKE lower(concat('%',:EmployeeName,'%')) ")
    List<Employee> searchProjects(@Param("EmployeeName") String query);
}
