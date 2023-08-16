package com.example.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @Schema(
            description = "Usr First Name"
    )
    @NotBlank(message = "First name should not be blank or null")
    private String firstName;

    @Schema(
            description = "Usr Last Name"
    )
    @NotBlank(message = "Last name should not be blank or null")
    private String lastName;

    @Schema(
            description = "Usr Email Address"
    )
    @NotBlank(message = "Email should not be blank or null")
    @Email(message = "Invalid format for Email")
    private String email;
}
