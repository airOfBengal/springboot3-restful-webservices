package com.example.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "First name should not be blank or null")
    private String firstName;
    @NotBlank(message = "Last name should not be blank or null")
    private String lastName;
    @NotBlank(message = "Email should not be blank or null")
    @Email(message = "Invalid format for Email")
    private String email;
}
