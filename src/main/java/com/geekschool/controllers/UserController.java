package com.geekschool.controllers;

import com.geekschool.constants.Status;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.User;
import com.geekschool.mapper.UserMapper;
import com.geekschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users/")
public class UserController {

    private UserService userService;

    @GetMapping("current")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getCurrentUser() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.findByUsername(username);

        UserMapper userMapper = new UserMapper(user);

        return userMapper.convertToUserDto();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return userService.getAllUser();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserStatus(@PathVariable long id, @RequestParam Status status) {
        userService.updateStatusById(id, status);
    }


}
