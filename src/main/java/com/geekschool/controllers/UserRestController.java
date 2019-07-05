package com.geekschool.controllers;

import com.geekschool.config.security.AuthenticationUser;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.Role;
import com.geekschool.entity.Status;
import com.geekschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserRestController {

    private UserService userService;

    @GetMapping("current")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getCurrentUser(@AuthenticationPrincipal AuthenticationUser currentUser) {
        return userService.findByUsername(currentUser.getUsername());
    }

    // TODO: 2019-07-05 use dedicated query to filter only Teacher users
    @GetMapping("teacher")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getTeacher() {
        return userService.getAllUser().stream()
                .filter(userDto -> userDto.getRole().equals(Role.TEACHER))
                .collect(Collectors.toList());
    }

    // TODO: 2019-07-05 fix js
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
