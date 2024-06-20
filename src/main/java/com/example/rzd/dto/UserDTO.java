package com.example.rzd.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {

    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String password;
    private String post;
    private String roles;
    private Long creatorId;
    private String confirmPassword;
}