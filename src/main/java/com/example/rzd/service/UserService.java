package com.example.rzd.service;

import com.example.rzd.dto.UserDTO;
import com.example.rzd.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(User user);

    List<User> findAllUsers() ;

    User getUserById(Long id);
    void deleteUser(Long id);


    List<User> getUserByRole(String role, Long id);

}
