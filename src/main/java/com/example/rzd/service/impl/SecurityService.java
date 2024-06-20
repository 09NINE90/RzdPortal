package com.example.rzd.service.impl;

import com.example.rzd.dto.CustomUserDetails;
import com.example.rzd.entity.User;
import com.example.rzd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class SecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundUser = userRepository.findByUserName(username);
        if (foundUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(foundUser);
    }
}