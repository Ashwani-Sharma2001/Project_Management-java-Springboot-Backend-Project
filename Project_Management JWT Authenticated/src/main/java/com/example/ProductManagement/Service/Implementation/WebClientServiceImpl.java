package com.example.ProductManagement.Service.Implementation;

import com.example.ProductManagement.DTO.Response.GETResponse;
import com.example.ProductManagement.DTO.Response.UpdateResponse;
import com.example.ProductManagement.Service.Interface.WebClientService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class WebClientServiceImpl implements WebClientService {

//    private static final String Get_all_Employee_URL="http://localhost:9020/projects";

//    RestTemplate restTemplate=new RestTemplate();
//    @Override
//    public ResponseEntity<String> getProjects() {
//        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity=new HttpEntity<String>("parameters",httpHeaders);
//
//        ResponseEntity<String> response= restTemplate.exchange(Get_all_Employee_URL, HttpMethod.GET,entity, String.class);
//        return response;
//    }
    private WebClient webClient;

    public WebClientServiceImpl() {

        this.webClient = WebClient.builder().baseUrl("http://localhost:9020").build();
    }


@Override
@JsonProperty
public Flux<GETResponse> getProjects() {
    return webClient.get()
            .uri("/projects")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(GETResponse.class);
}

    @Override
    @JsonProperty
    public Flux<GETResponse> RestSearch(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("query", query)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(GETResponse.class);
    }

    @Override
    public Flux<UpdateResponse> Webupdate() {
        return null;
    }

}
