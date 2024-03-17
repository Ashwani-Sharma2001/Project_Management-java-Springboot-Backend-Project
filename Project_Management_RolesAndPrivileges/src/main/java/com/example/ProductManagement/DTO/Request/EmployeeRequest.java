package com.example.ProductManagement.DTO.Request;

import com.example.ProductManagement.DAO.Entity.Projects;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
   @NotNull
    private String employeeName;

    private String status;

    private List<ProjectRequest> currentProjectList;
}
