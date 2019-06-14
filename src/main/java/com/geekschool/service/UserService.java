package com.geekschool.service;

import com.geekschool.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> getAllUser();

    User findByUsername(String name);

    User findById(Long id);

    void delete(Long id);
}
