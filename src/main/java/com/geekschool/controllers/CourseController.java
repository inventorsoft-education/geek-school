package com.geekschool.controllers;

import com.geekschool.dto.CourseDto;
import com.geekschool.mapper.CourseMapper;
import com.geekschool.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("${api}/courses")
public class CourseController {

    private CourseService courseService;
    private CourseMapper courseMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDto> getSubjects() {
        return courseService.getCourses()
        .stream().map(course -> courseMapper.convertToSubjectDto(course))
                .collect(Collectors.toList());
    }
}
