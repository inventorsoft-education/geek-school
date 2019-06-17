package com.geekschool.service.impl;

import com.geekschool.constants.Status;
import com.geekschool.dto.UserDto;
import com.geekschool.mapper.UserDtoFactory;
import com.geekschool.entity.Role;
import com.geekschool.entity.User;
import com.geekschool.repository.RoleRepository;
import com.geekschool.repository.UserRepository;
import com.geekschool.service.UserService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public UserDto save(User user) {
        Role role = new Role();

        role.setName("STUDENT");
        role.setUser(user);

        roleRepository.save(role);

        List<Role> roles = roleRepository.findAll();

        user.setRoles(roles);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);


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
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
