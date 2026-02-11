package com.example.auth_api.repository;

import jakarta.persistence.*;


import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;
    private String email;
    @Column(name = "password_hash")
    private String passwordHash;

    public UserEntity(String email, String passwordHash) {
        this.passwordHash = passwordHash;
        this.email = email;
    }

    public UserEntity() {
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password_hash) {
        this.passwordHash = password_hash;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
