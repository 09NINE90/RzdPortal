package com.example.rzd.mapper;

import com.example.rzd.dto.UserDTO;
import com.example.rzd.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public User convertToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

}