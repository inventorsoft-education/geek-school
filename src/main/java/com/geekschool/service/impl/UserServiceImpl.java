package com.geekschool.service.impl;

import com.geekschool.entity.User;
import com.geekschool.repository.RoleRepository;
import com.geekschool.repository.UserRepository;
import com.geekschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User findByUsername(String name) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
