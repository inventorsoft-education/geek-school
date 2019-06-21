package com.geekschool.service.impl;

import com.geekschool.constants.Status;
import com.geekschool.dto.UserDto;
import com.geekschool.constants.Role;
import com.geekschool.entity.User;
import com.geekschool.mapper.UserDtoFactory;
import com.geekschool.repository.UserRepository;
import com.geekschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
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

        return UserDtoFactory.convertToUserDto(user);
    }

    @Override
    @Transactional
    public List<UserDto> getAllUser() {
        List<UserDto> userList = userRepository.findAll()
                .stream()
                .map(user -> UserDtoFactory.convertToUserDto(user))
                .collect(Collectors.toList());
        return userList;
    }

    @Override
    @Transactional
    public UserDto findByUsername(String name) {
        User user = userRepository.findByUsername(name);
        return UserDtoFactory.convertToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto findById(Long id) {
        User user = userRepository.findById(id).get();
        return UserDtoFactory.convertToUserDto(user);
    }

    @Override
    @Transactional
    public void updateRole(long id, Role role) {
        User user = userRepository.getOne(id);
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
