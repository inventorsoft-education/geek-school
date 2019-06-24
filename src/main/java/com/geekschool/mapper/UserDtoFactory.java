package com.geekschool.mapper;

import com.geekschool.dto.AuthenticationLoginDto;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.User;
import com.geekschool.security.AuthenticationUser;

public class UserDtoFactory {

    public static UserDto convertToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getStatus(),
                user.getRole());
    }

    public static AuthenticationLoginDto convertToAuthenticationLoginDto(User user) {
        return new AuthenticationLoginDto(
                user.getUsername(),
                user.getPassword());
    }

    public static AuthenticationUser convertToAuthenticationUser(User user) {
        return new AuthenticationUser(user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                user.getStatus(),
                user.getRole());
    }
}
