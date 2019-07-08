package com.geekschool.service;

import com.geekschool.entity.Role;
import com.geekschool.entity.Status;
import com.geekschool.entity.User;
import com.geekschool.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Transactional
    public User findByUsername(String name) {
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public List<User> findTeacherByRole(Role role) {
        return userRepository.findByRole(role);
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
    public void saveUser(User user){

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
    }

    public void updateUserByPassword(String password, Long id){

        User user = userRepository.findById(id).orElseThrow();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
