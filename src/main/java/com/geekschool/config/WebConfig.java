package com.geekschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/users").setViewName("users");
        registry.addViewController("/users/current").setViewName("current");
        registry.addViewController("/element").setViewName("elements");
        registry.addViewController("/layout").setViewName("layout");
    }
}