package com.geekschool.config.security;

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
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByUsername(name)
                .map(u -> userMapper.convertToAuthenticationUser(u))
                .orElseThrow(() -> new UsernameNotFoundException("User with usernamae " + name + " not found"));
    }
}
