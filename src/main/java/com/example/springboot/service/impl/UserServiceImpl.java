package com.example.springboot.service.impl;

import com.example.springboot.entity.User;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    @Override
    public User createUser(User user) {
        return repository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<User> optionalUser = repository.findById(userId);
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = repository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = repository.save(existingUser);
        return updatedUser;
    }
}
