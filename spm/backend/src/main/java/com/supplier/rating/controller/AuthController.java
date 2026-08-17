package com.supplier.rating.controller;

import com.supplier.rating.model.entity.User;
import com.supplier.rating.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService service;
    public AuthController(AuthService service) { this.service=service; }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,Object> register(@RequestBody User user) {
        User saved=service.register(user);
        return Map.of("success",true,
            "data",Map.of("id",saved.getId(),"username",saved.getUsername(),
                          "name",saved.getName()==null?saved.getUsername():saved.getName(),
                          "role",saved.getRole()),
            "message","Registration successful");
    }

    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody Map<String,String> request) {
        User user=service.login(request.get("username"),request.get("password"));
        return Map.of("success",true,
            "data",Map.of("id",user.getId(),"username",user.getUsername(),
                          "name",user.getName()==null?user.getUsername():user.getName(),
                          "role",user.getRole()),
            "message","Login successful");
    }
}
