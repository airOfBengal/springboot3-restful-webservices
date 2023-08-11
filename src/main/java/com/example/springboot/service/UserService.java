package com.example.springboot.service;

import com.example.springboot.dto.UserDto;
import com.example.springboot.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto user);
    User getUserById(Long userId);
    List<User> getAllUser();
    User updateUser(User user);
    void deleteUser(Long userId);
}
