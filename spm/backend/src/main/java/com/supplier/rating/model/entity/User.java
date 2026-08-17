package com.supplier.rating.model.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true) private String username;
    @Column(nullable = false) private String password;
    @Column(nullable = false) private String role;
    private String name;
    private String email;
    private String phone;

    public User() {}
    public User(String username, String password, String role) {
        this.username=username; this.password=password; this.role=role;
    }
    public Long getId(){ return id; }
    public String getUsername(){ return username; }
    public void setUsername(String v){ username=v; }
    @JsonIgnore public String getPassword(){ return password; }
    public void setPassword(String v){ password=v; }
    public String getRole(){ return role; }
    public void setRole(String v){ role=v; }
    public String getName(){ return name; }
    public void setName(String v){ name=v; }
    public String getEmail(){ return email; }
    public void setEmail(String v){ email=v; }
    public String getPhone(){ return phone; }
    public void setPhone(String v){ phone=v; }
}
