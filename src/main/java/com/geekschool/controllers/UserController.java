package com.geekschool.controllers;

import com.geekschool.constants.Status;
import com.geekschool.entity.User;
import com.geekschool.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

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