package com.example.ProductManagement.Myconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class appConfig {

//    ------now we are commententing securityConfig out because we are creating custom user from Now


//    @Bean
//    public UserDetailsService userDetailsService() {
//        // You can build as many users as you want with their prescribed roles
//        UserDetails user1 = User.builder()
//                .username("ASHWANI")
//                .password(passwordEncoder().encode("ashwani"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("MANJU")
//                .password(passwordEncoder().encode("manju"))
//                .roles("ADMIN")
//                .build();
//
//        // Pass the users to the UserDetailsManager
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // or any other supported password encoder
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
