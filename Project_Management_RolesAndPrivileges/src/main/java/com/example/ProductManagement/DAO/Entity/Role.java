package com.example.ProductManagement.DAO.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import static com.example.ProductManagement.DAO.Entity.Permission.*;

@RequiredArgsConstructor
public enum Role {
//    //Use it when User Has No Access of API,s
//    USER(Collections.emptySet()),

    USER(
           Set.of(
                   USER_READ,USER_CREATE,USER_UPDATE,USER_DELETE
           )
    ),

    ADMIN(
            Set.of(
                    ADMIN_READ, ADMIN_CREATE, ADMIN_UPDATE, ADMIN_DELETE,
                    USER_READ,USER_CREATE,USER_UPDATE,USER_DELETE
            )
    )

    ;
    @Getter
    private final Set<Permission> permissions;


    //Reason for the granularity to give details to user their authority
    public List<SimpleGrantedAuthority> getAuthorities(){
      var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
              .collect(Collectors.toList());

      authorities.add(new SimpleGrantedAuthority(this.name()));
      return authorities;
    }
}
