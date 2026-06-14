package com.controller.gatedcommunity.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complaintId;
    @Column(name="username")
    private String username;
    private String category;
    @Column(length = 500)
    private String subject;
    @Column(length = 1000)
    private String description;
    private String email;
    private Long phNo;
    private String status="PENDING";

}
