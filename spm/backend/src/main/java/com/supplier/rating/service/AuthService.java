package com.supplier.rating.service;

import com.supplier.rating.model.entity.User;
import com.supplier.rating.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepository users;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository users) { this.users = users; }

    @Transactional
    public User register(User user) {
        if (user.getUsername()==null || user.getUsername().isBlank())
            throw new IllegalArgumentException("Username is required");
        if (user.getEmail()==null || user.getEmail().isBlank())
            throw new IllegalArgumentException("Email is required");
        if (user.getPhone()==null || user.getPhone().isBlank())
            throw new IllegalArgumentException("Phone number is required");
        String p=user.getPassword();
        if (p==null || !p.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$"))
            throw new IllegalArgumentException("Password must contain at least 8 characters, one uppercase letter, one number and one symbol");
        if (users.existsByUsernameIgnoreCase(user.getUsername()))
            throw new IllegalArgumentException("Username already exists");
        user.setPassword(encoder.encode(p));
        if (user.getRole()==null || user.getRole().isBlank()) user.setRole("USER");
        return users.save(user);
    }

    public User login(String username, String password) {
        User user=users.findByUsernameIgnoreCase(username)
            .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        if (password==null || !encoder.matches(password,user.getPassword()))
            throw new IllegalArgumentException("Invalid username or password");
        return user;
    }
}
