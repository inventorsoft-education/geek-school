package com.geekschool.controllers;

import com.geekschool.dto.CourseDto;
import com.geekschool.entity.Course;
import com.geekschool.mapper.CourseMapper;
import com.geekschool.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CourseDto createCourseOnTheTemplate(@RequestParam("id_course_template") Long id,
                                               @RequestParam("creation_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startDate,
                                               @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endDate) {
        Course course = courseService.createCourseOnTheTemplate(id, startDate, endDate);
        return courseMapper.convertToSubjectDto(course);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

}
