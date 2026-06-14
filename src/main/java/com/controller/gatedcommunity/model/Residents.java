package com.controller.gatedcommunity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="residents")
public class Residents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(unique = true)
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private String role;
    @Column(name = "phno")
    private Long phNo;

    private String secret;

    public Residents(String email, String fullName, String password, Long phNo, String role, String userName) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phNo = phNo;
        this.role = role;
        this.userName = userName;
    }
}
