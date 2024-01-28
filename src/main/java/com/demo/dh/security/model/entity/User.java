package com.demo.dh.security.model.entity;

import com.demo.dh.security.util.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
