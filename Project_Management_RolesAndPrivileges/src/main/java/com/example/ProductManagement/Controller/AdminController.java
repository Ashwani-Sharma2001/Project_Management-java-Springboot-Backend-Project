package com.example.ProductManagement.Controller;

import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/get")
    public String get(){
        return "GET:: admin Controller";
    }
    @PostMapping("/post")
    public String post(){
        return "POST:: admin Controller";
    }
    @PutMapping("/put")
    public String put(){
        return "PUT:: admin Controller";
    }
    @DeleteMapping("/delete")
    public String delete(){
        return "DELETE:: admin Controller";
    }
}
