package com.geekschool.config.security;

import com.geekschool.entity.User;
import com.geekschool.mapper.UserMapper;
import com.geekschool.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException("User with usernamae " + name + " not found");
        }
        UserMapper userMapper = new UserMapper(user);

        return userMapper.convertToAuthenticationUser();
    }
}
