package com.example.rzd.service.impl;

import com.example.rzd.dto.UserDTO;
import com.example.rzd.entity.User;
import com.example.rzd.mapper.UserMapper;
import com.example.rzd.repository.UserRepository;
import com.example.rzd.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    @Override
    public UserDTO createUser(User user) {
//        User userToSave = mapper.convertToEntity(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return mapper.convertToDto(savedUser);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    @Transactional
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }



    @Override
    public List<User> getUserByRole(String role, Long id) {
        return userRepository.findUserByRoles(role, id);
    }




}
