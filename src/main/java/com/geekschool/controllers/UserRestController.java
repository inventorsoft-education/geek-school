package com.geekschool.controllers;

import com.geekschool.config.security.AuthenticationUser;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.Role;
import com.geekschool.entity.Status;
import com.geekschool.entity.User;
import com.geekschool.mapper.UserMapper;
import com.geekschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserRestController {

    private UserService userService;
    private UserMapper userMapper;

    @GetMapping("current")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getCurrentUser(@AuthenticationPrincipal AuthenticationUser currentUser) {
        User user = userService.findByEmail(currentUser.getEmail());
        return userMapper.convertToUserDto(user);
    }

    @GetMapping("teacher")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getTeacher() {
        return userService.findTeacherByRole(Role.TEACHER)
                .stream()
                .map(user -> userMapper.convertToUserDto(user))
                .collect(Collectors.toList());

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        return userService.getAllUser()
                .stream()
                .map(user -> userMapper.convertToUserDto(user))
                .collect(Collectors.toList());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserStatus(@PathVariable long id, @RequestParam Status status) {
        userService.updateStatusById(id, status);
    }

    @GetMapping(value = "/users/user")
    private void saveUser(@RequestParam Long id, String username, String password)
    {
        userService.saveUserByInvitation(id, username, password);
    }


    @PostMapping(value = "/users/password")
    public void updateUserByPassword(@RequestParam String password, Long id){
        userService.updateUserByPassword(password, id);
    }

}
