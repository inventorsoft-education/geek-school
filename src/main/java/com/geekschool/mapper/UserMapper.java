package com.geekschool.mapper;

import com.geekschool.config.security.AuthenticationUser;
import com.geekschool.dto.AuthenticationLoginDto;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {
    private GroupMapper groupMapper;

    public UserDto convertToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getStatus(),
                user.getRole(),
                user.getGroups()
                        .stream()
                        .map(group -> groupMapper.convertToGroupDto(group))
                        .collect(Collectors.toList()));
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
                user.getPassword(),
                user.getStatus(),
                user.getRole());
    }
}
