package com.geekschool.service;

import com.geekschool.dto.UserDto;
import com.geekschool.entity.Role;
import com.geekschool.entity.User;

import java.util.List;

public interface UserService {

    UserDto save(User user);

    List<UserDto> getAllUser();

    UserDto findByUsername(String name);

    UserDto findById(Long id);

    void delete(Long id);
}
