package com.example.ProductManagement.DTO.Request;

import com.example.ProductManagement.DAO.Entity.Projects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {

    private String employeeName;
    private String status;
    private List<ProjectRequest> currentProjectList;
}
