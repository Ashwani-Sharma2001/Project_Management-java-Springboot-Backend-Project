package com.example.ProductManagement.DAO.Entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Transactional
@NoArgsConstructor
@AllArgsConstructor
@Table(name="employee")
public class Employee {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    @Id
    private Integer id;

    @Column(name="employee_name")
    private  String EmployeeName;

    @OneToMany(targetEntity = Projects.class)
    @JoinColumn(name="fk_empid" ,referencedColumnName = "id")
    @Column(name="current_projects")
    private List<Projects> CurrentProjects;

    @Column(name="status")
    private String Status;
}
