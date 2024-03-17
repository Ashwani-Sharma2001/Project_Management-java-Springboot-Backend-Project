package com.example.ProductManagement.Controller;

import com.example.ProductManagement.DAO.Entity.User;
import com.example.ProductManagement.DTO.Request.EmployeeRequest;
import com.example.ProductManagement.DTO.Response.GETResponse;
import com.example.ProductManagement.DTO.Response.POSTResponse;
import com.example.ProductManagement.DTO.Response.UpdateResponse;
import com.example.ProductManagement.Service.Interface.ProjectService;
import com.example.ProductManagement.Service.Interface.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class ProjectController {
    @Autowired
     private ProjectService projectService;
     @Autowired
    private UserInterface userInterface;
    @PostMapping("/addProjectDetails")
    public ResponseEntity<POSTResponse> addProject(@RequestBody EmployeeRequest empRequest){
        return projectService.addProject(empRequest);
    }
    @GetMapping("/projects")
    public ResponseEntity<List<GETResponse>> getProjects(){
        return projectService.getProject();
    }

    @CrossOrigin
    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<UpdateResponse> updateEmp(@RequestBody EmployeeRequest updateDetails, @PathVariable Integer id){
        return projectService.updateEmp(updateDetails,id);
    }
    @GetMapping("/search")
    public ResponseEntity<List<GETResponse>> searchProjects(@RequestParam("query") String query){
        return projectService.searchProjects(query);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UpdateResponse> deleteProject(@PathVariable Integer id){
        return projectService.deleteProject(id);
    }
//    --------------------------
   @GetMapping("/users")
    public List<User> getUsers(){
        return userInterface.getUsers();
    }
    @GetMapping("/current-user")
    public String getLoggedInUser(Principal principal){
        return principal.getName();
    }
}
