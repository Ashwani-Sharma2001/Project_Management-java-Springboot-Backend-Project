package com.example.ProductManagement.Controller;

import com.example.ProductManagement.DTO.Request.EmployeeRequest;
import com.example.ProductManagement.DTO.Request.UpdateRequest;
import com.example.ProductManagement.DTO.Response.GETResponse;
import com.example.ProductManagement.DTO.Response.POSTResponse;
import com.example.ProductManagement.DTO.Response.UpdateResponse;
import com.example.ProductManagement.Service.Interface.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/addProjectDetails")
    public ResponseEntity<POSTResponse> addProject(@RequestBody EmployeeRequest empRequest){
        return projectService.addProject(empRequest);
    }
    @GetMapping("/projects")
    public ResponseEntity<List<GETResponse>> getProjects(){
        return projectService.getProject();
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<UpdateResponse> updateEmp(@RequestBody EmployeeRequest updateDetails, @PathVariable Integer id){
        return projectService.updateEmp(updateDetails,id);
    }

}
