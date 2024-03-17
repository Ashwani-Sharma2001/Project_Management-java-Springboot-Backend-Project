package com.example.ProductManagement.DTO.Request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JWTRequest {
     private String email;

     private String password;
}
