package com.geekschool.controllers;

import com.geekschool.dto.courses.CourseDto;
import com.geekschool.dto.courses.CourseLectionsDto;
import com.geekschool.entity.Course;
import com.geekschool.mapper.CourseMapper;
import com.geekschool.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CourseDto createCourseOnTheTemplate(@Valid @RequestBody CourseLectionsDto courseLectionsDto) {
        Course course = courseService.createCourseOnTheTemplate(courseLectionsDto);
        return courseMapper.convertToSubjectDto(course);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

}
