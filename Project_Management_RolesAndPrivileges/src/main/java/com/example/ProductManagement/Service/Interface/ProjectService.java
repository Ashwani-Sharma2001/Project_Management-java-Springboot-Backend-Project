package com.example.ProductManagement.Service.Interface;

import com.example.ProductManagement.DTO.Request.EmployeeRequest;

import com.example.ProductManagement.DTO.Response.GETResponse;
import com.example.ProductManagement.DTO.Response.POSTResponse;
import com.example.ProductManagement.DTO.Response.UpdateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProjectService {
    ResponseEntity<POSTResponse> addProject(EmployeeRequest empRequest);

    ResponseEntity<List<GETResponse>> getProject();

    ResponseEntity<UpdateResponse> updateEmp(EmployeeRequest updateDetails, Integer id);

    ResponseEntity<List<GETResponse>> searchProjects(String query);

    ResponseEntity<UpdateResponse> deleteProject(Integer id);
}
