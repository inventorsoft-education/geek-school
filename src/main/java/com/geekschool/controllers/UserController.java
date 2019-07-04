package com.geekschool.controllers;

import com.geekschool.constants.Status;
import com.geekschool.dto.UserDto;
import com.geekschool.mapper.UserMapper;
import com.geekschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;
    private UserMapper userMapper;

    @GetMapping("current")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getCurrentUser() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserDto user = userService.findByUsername(username);

        return user;
    }

    @GetMapping("admin")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return userService.getAllUser();
    }

    @GetMapping("admin/status")
    public List<Status> getStatus() {
        return Arrays.stream(Status.values()).collect(Collectors.toList());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserStatus(@PathVariable long id, @RequestParam Status status) {
        userService.updateStatusById(id, status);
    }


}
