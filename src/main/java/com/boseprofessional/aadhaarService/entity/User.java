package com.boseprofessional.aadhaarService.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
@Table(name = "users", indexes = {@Index(name = "idx_name", columnList = "name")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "name is mandatory")
    @Column(nullable = false)
    private String name;

    @Email(message = "give valid email")
    @NotBlank(message = "it is a required field")
    @Column(nullable = false,  unique = true)
    private String email;

    @NotBlank(message = "it is a required field")
    @Column(nullable = false,  unique = true, name = "aadhaar_id")
    @Pattern(regexp = "^\\d{12}$", message = "Aadhaar id must be of 12 digit")
    private String aadhaarId;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be of 10 digits")
    private String phoneNumber;

    @Column(columnDefinition = "TEXT")
    private String address;
}
