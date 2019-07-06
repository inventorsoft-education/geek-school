package com.geekschool.config.security;

import com.geekschool.entity.Role;
import com.geekschool.entity.Status;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id", "username"})
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationUser implements UserDetails {

    private long id;
    private String username;
    private String password;
    private Status status;
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !status.equals(Status.NOT_ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !status.equals(Status.DELETED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
