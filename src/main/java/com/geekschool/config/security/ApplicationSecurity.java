package com.geekschool.config.security;

import com.geekschool.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    private static final String ADMIN_ENDPOINT = "/admin/**";
    private static final String TEACHER_ENDPOINT = "/teacher/**";
    private static final String LOGIN_ENDPOINT = "/login";
    private static final String LOGOUT_ENDPOINT = "/logout";

    private AuthenticationUserDetailService authenticationUserDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT, "/css/**", "/js/**", "/scss/**", "/img/**", "/fonts/**",
                        "/forgotPassword","/forgot-password-send", "/user/password/{token}", "/user/{token}", "/users/password", "/users/user", "/api/users/users/user").permitAll()
                .antMatchers(ADMIN_ENDPOINT, "/api/lection/**", "/lection/**", "/api/groups/admin/**",
                        "/groups", "/api/users/admin/**", "/users").hasAuthority(Role.ADMIN.getAuthority())
                .antMatchers(TEACHER_ENDPOINT).hasAuthority(Role.TEACHER.getAuthority())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(LOGIN_ENDPOINT)
                .loginProcessingUrl(LOGIN_ENDPOINT)
                .successHandler(new DefaultAuthenticationSuccessHandler())
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_ENDPOINT))
                .logoutSuccessUrl(LOGIN_ENDPOINT);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationUserDetailService)
                .passwordEncoder(encoder());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
