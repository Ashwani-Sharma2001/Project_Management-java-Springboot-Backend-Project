package com.example.ProductManagement.Service.Interface;

import com.example.ProductManagement.DAO.Entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserInterface {
    public List<User> getUsers();

    public User createUser(User user);
}
