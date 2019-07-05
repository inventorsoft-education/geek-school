package com.geekschool.mapper;

import com.geekschool.config.security.AuthenticationUser;
import com.geekschool.dto.AuthenticationLoginDto;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto convertToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getStatus(),
                user.getRole(),
                user.getGroups());
    }

    public AuthenticationLoginDto convertToAuthenticationLoginDto(User user) {
        return new AuthenticationLoginDto(
                user.getUsername(),
                user.getPassword());
    }

    public AuthenticationUser convertToAuthenticationUser(User user) {
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
