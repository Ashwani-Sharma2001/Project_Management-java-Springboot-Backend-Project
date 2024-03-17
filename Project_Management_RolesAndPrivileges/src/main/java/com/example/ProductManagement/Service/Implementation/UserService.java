package com.example.ProductManagement.Service.Implementation;

import com.example.ProductManagement.DAO.Entity.User;
import com.example.ProductManagement.DAO.Repository.UserRepository;
import com.example.ProductManagement.Service.Interface.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class UserService implements UserInterface {
@Autowired
private UserRepository userRepository;
@Autowired
private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        user.setUserid(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
