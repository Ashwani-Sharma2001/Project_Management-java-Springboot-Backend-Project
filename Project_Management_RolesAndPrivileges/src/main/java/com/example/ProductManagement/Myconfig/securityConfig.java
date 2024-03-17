package com.example.ProductManagement.Myconfig;




import com.example.ProductManagement.Security.JWTAuthenticationEntryPoint;
import com.example.ProductManagement.Security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.example.ProductManagement.DAO.Entity.Permission.*;
import static com.example.ProductManagement.DAO.Entity.Role.ADMIN;
import static com.example.ProductManagement.DAO.Entity.Role.USER;
import static org.springframework.http.HttpMethod.*;


@Configuration
public class securityConfig {
    @Autowired
   private JWTAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeRequests().
                requestMatchers("/test").authenticated().requestMatchers("/auth/login").permitAll()
                .requestMatchers("/auth/createUser").permitAll()

                // add more roles as you want just define there accessibility in Role interafce and create
                // the instance of authority here in .hasAnyAuthority
                .requestMatchers("/admin/**").hasAnyAuthority(ADMIN.name(), USER.name())


    //add the api access authority here to define who can access api based on their role
                .requestMatchers(GET,"/get").hasAnyAuthority(ADMIN_READ.name(),USER_READ.name())
                .requestMatchers(PUT,"/put").hasAnyAuthority(ADMIN_UPDATE.name(),USER_UPDATE.name())
                .requestMatchers(DELETE,"/delete").hasAnyAuthority(ADMIN_DELETE.name(),USER_DELETE.name())
                .requestMatchers(POST,"/post").hasAnyAuthority(ADMIN_CREATE.name(),USER_CREATE.name())


                .requestMatchers(PUT,"/user/**").hasAuthority(USER_UPDATE.name())
                .requestMatchers(DELETE,"/user/**").hasAuthority(USER_DELETE.name())
                .requestMatchers(POST,"/user/**").hasAuthority(USER_CREATE.name())

                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
//----------------write when db connection go on
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return daoAuthenticationProvider;
    }
}
