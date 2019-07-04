package com.geekschool.service;

import com.geekschool.constants.Role;
import com.geekschool.constants.Status;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.User;
import com.geekschool.mapper.UserMapper;
import com.geekschool.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private UserMapper userMapper;

    @Transactional
    public UserDto save(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);

        user.setRole(Role.STUDENT);

        userRepository.save(user);

        return userMapper.convertToUserDto(user);
    }

    @Transactional
    public List<UserDto> getAllUser() {
        List<UserDto> userList = userRepository.findAll()
                .stream()
                .map(user -> userMapper.convertToUserDto(user))
                .collect(Collectors.toList());
        return userList;
    }

    @Transactional
    public UserDto findByUsername(String name) {
        return userRepository.findByUsername(name)
                .map(user -> userMapper.convertToUserDto(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public UserDto findById(Long id) {
        return userRepository.findById(id)
                .map(user -> userMapper.convertToUserDto(user))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public void updateRoleById(long id, Role role) {
        userRepository.updateRoleById(id, role);
    }

    @Transactional
    public void updateStatusById(long id, Status status) {
        userRepository.updateStatusById(id, status);
    }

    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User createUserByToken(String formType, String email){
        User user = new User();
        user.setEmail(email);
        if(Role.STUDENT.toString().equals(formType)){
            user.setRole(Role.STUDENT);
        }
        else {
            user.setRole(Role.TEACHER);
        }

        userRepository.save(user);
        return user;
    }
}
