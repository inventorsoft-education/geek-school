package com.geekschool.service;

import com.geekschool.constants.Role;
import com.geekschool.constants.Status;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.User;
import com.geekschool.mapper.UserMapper;
import com.geekschool.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public User findByUsername(String name) {
        User user = userRepository.findByUsername(name);
        return user;
    }

    @Transactional
    public User findById(Long id) {
        User user = userRepository.findById(id).get();
        return user;
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
}
