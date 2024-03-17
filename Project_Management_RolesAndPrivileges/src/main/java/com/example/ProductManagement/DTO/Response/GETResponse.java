package com.example.ProductManagement.DTO.Response;

import com.example.ProductManagement.DAO.Entity.Projects;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Data
@ResponseBody
@AllArgsConstructor
@NoArgsConstructor
public class GETResponse {
    private String employeeName;
    private List<Projects> currentProject;
    private String status;
    private Integer id;

}
