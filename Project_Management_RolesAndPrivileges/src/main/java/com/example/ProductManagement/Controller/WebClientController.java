package com.example.ProductManagement.Controller;

import com.example.ProductManagement.DTO.Response.GETResponse;
import com.example.ProductManagement.DTO.Response.UpdateResponse;
import com.example.ProductManagement.Service.Interface.WebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/RestTemplate")
public class WebClientController {
    @Autowired
    WebClientService restTemplateService;

    @GetMapping("/allprojects")
    public Flux<GETResponse> getProjects(){
        return restTemplateService.getProjects();
    }
     @GetMapping("/RestSearch")
    public Flux<GETResponse> RestSearch(@RequestParam("query") String query){
        return restTemplateService.RestSearch(query);
    }

    public Flux<UpdateResponse> WebUpdate(){
        return restTemplateService.Webupdate();
    }
}
