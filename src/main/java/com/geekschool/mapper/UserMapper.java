package com.geekschool.mapper;

import com.geekschool.dto.AuthenticationLoginDto;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.User;
import com.geekschool.config.security.AuthenticationUser;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserMapper {

    private User user;

    public UserDto convertToUserDto() {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getStatus(),
                user.getRole());
    }

    public AuthenticationLoginDto convertToAuthenticationLoginDto() {
        return new AuthenticationLoginDto(
                user.getUsername(),
                user.getPassword());
    }

    public AuthenticationUser convertToAuthenticationUser() {
        return new AuthenticationUser(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus(),
                user.getRole());
    }
}
