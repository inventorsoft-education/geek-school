package com.geekschool.service;

import com.geekschool.dto.UserDto;
import com.geekschool.entity.Role;
import com.geekschool.entity.Status;
import com.geekschool.entity.User;
import com.geekschool.mapper.UserMapper;
import com.geekschool.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private UserMapper userMapper;


    @Transactional
    public List<UserDto> getAllUser() {
        return userRepository.findAll()
                .stream()
                .map(user -> userMapper.convertToUserDto(user))
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto findByUsername(String name) {
        return userRepository.findByUsername(name)
                .map(user -> userMapper.convertToUserDto(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public void updateStatusById(long id, Status status) {
        userRepository.updateStatusById(id, status);
    }

    public User createUserByToken(Role formType, String email) {
        User user = new User();
        user.setEmail(email);
        user.setRole(formType);
        userRepository.save(user);
        return user;
    }
}
