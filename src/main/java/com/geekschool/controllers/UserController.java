package com.geekschool.controllers;

import com.geekschool.constants.Role;
import com.geekschool.constants.Status;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.User;
import com.geekschool.mapper.UserMapper;
import com.geekschool.repository.UserRepository;
import com.geekschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("current")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getCurrentUser() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserDto user = userService.findByUsername(username);

        return user;
    }

    @GetMapping("teacher")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getTeacher() {
        return userService.getAllUser().stream()
                .filter(userDto -> userDto.getRole().equals(Role.TEACHER))
                .collect(Collectors.toList());
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

    private UserRepository userRepository;
    private PasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/saveUser")
    private String saveUser(@ModelAttribute("user") User user){

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        return "blog-home";
    }

    @PostMapping(value = "/updateUserByPassword")
    public String updateUserByPassword(@RequestParam Long id, String password){

        Optional<User> user1 = userRepository.findById(id);
        User user;
        if(user1.isPresent()){
            user = user1.get();
            user.setPassword(bCryptPasswordEncoder.encode(password));
            userRepository.save(user);
            return "blog-home";
        }
        else {
            return null; //TODO sth
        }
    }

}
