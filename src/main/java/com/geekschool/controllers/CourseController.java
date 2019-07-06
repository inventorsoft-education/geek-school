package com.geekschool.controllers;

import com.geekschool.dto.SubjectDto;
import com.geekschool.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${api}/courses")
public class CourseController {

    private CourseService courseService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubjectDto> getSubjects() {
        return courseService.getSubjects();
    }
}
