package com.geekschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/groups").setViewName("groups");
        registry.addViewController("/users").setViewName("users/users");
        registry.addViewController("/lection").setViewName("lections/lection");
        registry.addViewController("/users/current").setViewName("users/current");
        registry.addViewController("/courses-templates/new").setViewName("courses/create-course-template");
    }
}
