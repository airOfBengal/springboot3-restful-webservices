package com.example.springboot.service.impl;

import com.example.springboot.dto.UserDto;
import com.example.springboot.entity.User;
import com.example.springboot.exception.EmailAlreadyExistsException;
import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.UserService;
import com.example.springboot.utils.UserMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        //User user = UserMapper.mapToUser(userDto);

        Optional<User> optionalUser = repository.findUserByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exists for User");
        }

        User user = modelMapper.map(userDto, User.class);
        User savedUser = repository.save(user);
        //UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = repository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        //UserDto user = UserMapper.mapToUserDto(optionalUser.get());
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> users = repository.findAll();
        //return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
        return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User existingUser = repository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setEmail(userDto.getEmail());
        User updatedUser = repository.save(existingUser);
        //UserDto updatedUserDto = UserMapper.mapToUserDto(updatedUser);
        UserDto updatedUserDto = modelMapper.map(updatedUser, UserDto.class);
        return updatedUserDto;
    }

    @Override
    public void deleteUser(Long userId) {
        User existingUser = repository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        repository.deleteById(userId);
    }
}
