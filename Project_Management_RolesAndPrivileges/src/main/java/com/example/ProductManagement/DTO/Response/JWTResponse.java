package com.example.ProductManagement.DTO.Response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JWTResponse {

    private String jwtToken;

    private String  username;
}
