package com.example.ProductManagement.Service.Implementation;

import com.example.ProductManagement.DAO.Entity.Employee;
import com.example.ProductManagement.DAO.Entity.Projects;
import com.example.ProductManagement.DAO.Repository.EmployeeRepository;
import com.example.ProductManagement.DAO.Repository.ProjectsRepositpory;
import com.example.ProductManagement.DTO.Request.EmployeeRequest;
import com.example.ProductManagement.DTO.Request.ProjectRequest;
import com.example.ProductManagement.DTO.Response.GETResponse;
import com.example.ProductManagement.DTO.Response.POSTResponse;
import com.example.ProductManagement.DTO.Response.UpdateResponse;
import com.example.ProductManagement.Service.Interface.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectsRepositpory projectsRepositpory;
    POSTResponse projectResponse = new POSTResponse("Project Added Successsfully...");
    UpdateResponse updateResponse = new UpdateResponse("Project Updated Successfully...");

    UpdateResponse deleteResponse=new UpdateResponse("Project Details Removed Successfully");

    @Override
    public ResponseEntity<POSTResponse> addProject(EmployeeRequest empRequest) {
        try {
            Employee empTable = new Employee();
            empTable.setEmployeeName(empRequest.getEmployeeName());
            empTable.setStatus(empRequest.getStatus());
            List<Projects> projectsList = new ArrayList<>();
            for (ProjectRequest projectRequest : empRequest.getCurrentProjectList()) {
                Projects projects = new Projects();
                projects.setCurrentProjects(projectRequest.getCurrentProject());
                projectsList.add(projects);
                projectsRepositpory.save(projects);
            }

            empTable.setCurrentProjects(projectsList);
            employeeRepository.save(empTable);
            return new ResponseEntity<>(projectResponse, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw e;
        }
    }


    @Override
    public ResponseEntity<List<GETResponse>> getProject() {
        List<Employee> empList = employeeRepository.findAll();
        List<GETResponse> getResponses = new ArrayList<>();
        GETResponse getResponse;
        try {
            for (Employee emp : empList) {
                getResponse = new GETResponse();
                getResponse.setId(emp.getId());
                getResponse.setEmployeeName(emp.getEmployeeName());
                getResponse.setCurrentProject(emp.getCurrentProjects());
                getResponse.setStatus(emp.getStatus());
                getResponses.add(getResponse);
            }
            return new ResponseEntity<>(getResponses, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ResponseEntity<UpdateResponse> updateEmp(EmployeeRequest updateDetails, Integer id) {
        UpdateResponse updateResponse = new UpdateResponse();
        try {
            Employee employee = employeeRepository.findById(id).orElse(null);
            if (employee == null) {
                updateResponse.setMessage("Employee not found");
                return new ResponseEntity<>(updateResponse, HttpStatus.NOT_FOUND);
            }

            employee.setEmployeeName(updateDetails.getEmployeeName());
            employee.setStatus(updateDetails.getStatus());

            List<Projects> existingProjects = employee.getCurrentProjects();
            List<ProjectRequest> updatedProjects = updateDetails.getCurrentProjectList();
            for (int i = 0; i < existingProjects.size() && i < updatedProjects.size(); i++) {
                Projects existingProject = existingProjects.get(i);
                ProjectRequest updatedProject = updatedProjects.get(i);
                existingProject.setCurrentProjects(updatedProject.getCurrentProject());
                projectsRepositpory.save(existingProject);
            }
            for (int i = existingProjects.size(); i < updatedProjects.size(); i++) {
                ProjectRequest updatedProject = updatedProjects.get(i);
                Projects newProject = new Projects();
                newProject.setCurrentProjects(updatedProject.getCurrentProject());
                projectsRepositpory.save(newProject);
                existingProjects.add(newProject);
            }
            while (existingProjects.size() > updatedProjects.size()) {
                Projects removedProject = existingProjects.remove(existingProjects.size() - 1);
                projectsRepositpory.delete(removedProject);
            }
            employeeRepository.save(employee);
            updateResponse.setMessage("Employee updated successfully");
            return new ResponseEntity<>(updateResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<GETResponse>> searchProjects(String query){
        List<Employee> empList = employeeRepository.searchProjects(query);
        List<GETResponse> getResponses = new ArrayList<>();
        GETResponse getResponse;
        try {
            for (Employee emp : empList) {
                getResponse = new GETResponse();
                getResponse.setId(emp.getId());
                getResponse.setEmployeeName(emp.getEmployeeName());
                getResponse.setCurrentProject(emp.getCurrentProjects());
                getResponse.setStatus(emp.getStatus());
                getResponses.add(getResponse);
            }

            return new ResponseEntity<>(getResponses, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ResponseEntity<UpdateResponse> deleteProject(Integer id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(deleteResponse,HttpStatus.OK);
    }

}