package com.geekschool.service.impl;

import com.geekschool.constants.Role;
import com.geekschool.constants.Status;
import com.geekschool.dto.UserDto;
import com.geekschool.entity.User;
import com.geekschool.mapper.UserMapper;
import com.geekschool.repository.UserRepository;
import com.geekschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public UserDto save(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);

        user.setRole(Role.STUDENT);

        userRepository.save(user);

        UserMapper userMapper = new UserMapper(user);

        return userMapper.convertToUserDto();
    }

    @Override
    @Transactional
    public List<UserDto> getAllUser() {
        List<UserDto> userList = userRepository.findAll()
                .stream()
                .map(user -> new UserMapper(user).convertToUserDto())
                .collect(Collectors.toList());
        return userList;
    }

    @Override
    @Transactional
    public User findByUsername(String name) {
        User user = userRepository.findByUsername(name);
        return user;
    }

    @Override
    @Transactional
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).get();
        UserMapper userMapper = new UserMapper(user);
        return userMapper.convertToUserDto();
    }

    @Override
    @Transactional
    public void updateRoleById(long id, Role role) {
        userRepository.updateRoleById(id, role);
    }

    @Override
    @Transactional
    public void updateStatusById(long id, Status status) {
        userRepository.updateStatusById(id, status);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
