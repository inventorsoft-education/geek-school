package com.geekschool.mapper;

import com.geekschool.dto.AuthenticationLoginDto;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.User;

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
}
