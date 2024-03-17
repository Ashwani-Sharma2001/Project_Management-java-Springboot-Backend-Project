package com.example.ProductManagement.Service.Interface;

import com.example.ProductManagement.DTO.Request.EmployeeRequest;
import com.example.ProductManagement.DTO.Request.UpdateRequest;
import com.example.ProductManagement.DTO.Response.GETResponse;
import com.example.ProductManagement.DTO.Response.POSTResponse;
import com.example.ProductManagement.DTO.Response.UpdateResponse;
import org.hibernate.sql.Update;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProjectService {
    ResponseEntity<POSTResponse> addProject(EmployeeRequest empRequest);

    ResponseEntity<List<GETResponse>> getProject();

    ResponseEntity<UpdateResponse> updateEmp(EmployeeRequest updateDetails, Integer id);
}
