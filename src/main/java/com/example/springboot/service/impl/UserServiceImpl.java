package com.example.springboot.service.impl;

import com.example.springboot.dto.UserDto;
import com.example.springboot.entity.User;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.UserService;
import com.example.springboot.utils.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = repository.save(user);
        UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = repository.findById(userId);
        UserDto user = UserMapper.mapToUserDto(optionalUser.get());
        return user;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<User> users = repository.findAll();
        for(User user: users){
            userDtoList.add(UserMapper.mapToUserDto(user));
        }
        return userDtoList;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = repository.findById(userDto.getId()).get();
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = repository.save(existingUser);
        UserDto updatedUserDto = UserMapper.mapToUserDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(Long userId) {
        repository.deleteById(userId);
    }
}
