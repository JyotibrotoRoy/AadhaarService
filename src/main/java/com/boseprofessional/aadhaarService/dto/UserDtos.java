package com.boseprofessional.aadhaarService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserDtos {
    public record CreateUserRequest(
            @NotBlank(message = "Name is mandatory")
            String name,

            @NotBlank(message = "Email is mandatory")
            @Email(message = "given email is in invalid fromat")
            String email,

            @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
            String phoneNumber,

            @NotBlank(message = "provide Aadhaar ID")
            @Pattern(regexp = "^\\d{12}$", message = "Aadhaar ID must be exactly 12 digits")
            String aadhaarId,

            String address
    ){}

    public record UserResponse(
            Long id,
            String name,
            String email,
            String aadhaarId,
            String phoneNumber,
            String address
    ){}
}
