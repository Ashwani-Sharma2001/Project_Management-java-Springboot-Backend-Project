package com.example.ProductManagement.Service.Implementation;

import com.example.ProductManagement.DAO.Entity.Employee;
import com.example.ProductManagement.DAO.Entity.Projects;
import com.example.ProductManagement.DAO.Repository.EmployeeRepository;
import com.example.ProductManagement.DAO.Repository.ProjectsRepositpory;
import com.example.ProductManagement.DTO.Request.EmployeeRequest;
import com.example.ProductManagement.DTO.Request.ProjectRequest;
import com.example.ProductManagement.DTO.Request.UpdateRequest;
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
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<>(projectResponse, HttpStatus.ACCEPTED);
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
                // Handle the case where the employee with the given id is not found
                updateResponse.setMessage("Employee not found");
                return new ResponseEntity<>(updateResponse, HttpStatus.NOT_FOUND);
            }

            // Update Employee details
            employee.setEmployeeName(updateDetails.getEmployeeName());
            employee.setStatus(updateDetails.getStatus());

            // Update Projects
            List<Projects> existingProjects = employee.getCurrentProjects();
            List<ProjectRequest> updatedProjects = updateDetails.getCurrentProjectList();

            // Update existing projects
            for (int i = 0; i < existingProjects.size() && i < updatedProjects.size(); i++) {
                Projects existingProject = existingProjects.get(i);
                ProjectRequest updatedProject = updatedProjects.get(i);
                existingProject.setCurrentProjects(updatedProject.getCurrentProject());
                projectsRepositpory.save(existingProject);
            }

            // Add new projects if the updated list is longer
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


//@Override
//    public ResponseEntity<UpdateResponse> updateEmp(EmployeeRequest updateDetails, Integer id) {
//        UpdateResponse updateResponse = new UpdateResponse();
//        try {
//            // Get the existing employee
//            Employee employee = employeeRepository.findById(id).orElse(null);
//            if (employee != null) {
//                // Update employee details
//                employee.setEmployeeName(updateDetails.getEmployeeName());
//                employee.setStatus(updateDetails.getStatus());
//
//                // Create a new list to store updated projects
//                List<Projects> updatedProjects = new ArrayList<>();
//
//                // Update or add projects
//                for (ProjectRequest projectRequest : updateDetails.getCurrentProjectList()) {
//                    Projects project;
//                    if (projectRequest.getId() != null) {
//                        // If the project has an ID, it means it already exists, so update it
//                        project = projectsRepository.findById(projectRequest.getId()).orElse(null);
//                        if (project != null) {
//                            project.setCurrentProjects(projectRequest.getCurrentProject());
//                            project = projectsRepository.save(project);
//                        }
//                    } else {
//                        // If the project has no ID, it means it's a new project, so create and save it
//                        project = new Projects();
//                        project.setCurrentProjects(projectRequest.getCurrentProject());
//                        project.setEmployee(employee); // Establish the foreign key relationship
//                        project = projectsRepository.save(project);
//                    }
//                    updatedProjects.add(project);
//                }
//
//                // Update the list of projects associated with the employee
//                employee.setCurrentProjects(updatedProjects);
//
//                // Save the updated employee
//                employeeRepository.save(employee);
//
//                updateResponse.setMessage("Employee updated successfully.");
//                return new ResponseEntity<>(updateResponse, HttpStatus.OK);
//            } else {
//                // Handle the case where the employee with the given ID is not found
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            // Handle any exceptions
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

}