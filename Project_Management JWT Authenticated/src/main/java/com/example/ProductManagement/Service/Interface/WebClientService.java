package com.example.ProductManagement.Service.Interface;

import com.example.ProductManagement.DTO.Response.GETResponse;
import com.example.ProductManagement.DTO.Response.UpdateResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WebClientService {
    public Flux<GETResponse> getProjects();


    Flux<GETResponse> RestSearch(String query);

    Flux<UpdateResponse> Webupdate();
}
