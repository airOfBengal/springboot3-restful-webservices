package com.example.springboot.controller;

import com.example.springboot.dto.UserDto;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ErrorDetails;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@Tag(
        name = "CRUD REST APIs for User Resource",
        description = "Create User, Update User, Get User, Get All User, Delete User"
)
@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    private UserService service;

    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save user in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto savedUser = service.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get User by ID REST API",
            description = "Grt User by ID REST API is used to get a single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = service.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Get All Users ID REST API",
            description = "Grt All Users REST API is used to get all the users from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(service.getAllUser());
    }

    @Operation(
            summary = "Update User by ID REST API",
            description = "Update User by ID REST API is used to Update a single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto, @PathVariable("id") Long userId){
        userDto.setId(userId);
        UserDto updatedUser = service.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
            summary = "Delete User by ID REST API",
            description = "Delete User by ID REST API is used to Delete a single user from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        service.deleteUser(userId);
        return ResponseEntity.ok("User successfully deleted!");
    }

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
}
