package com.geekschool.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO: 2019-07-05 refactor to view controllers
@Controller
@RequestMapping(value = "/")
public class IndexController {
    @GetMapping
    static String getIndex() {
        return "blog-home";
    }

    @GetMapping(value = "/elements")
    static String getElements() {
        return "elements";
    }


}
