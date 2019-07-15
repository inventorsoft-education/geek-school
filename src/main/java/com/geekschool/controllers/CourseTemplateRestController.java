package com.geekschool.controllers;

import com.geekschool.dto.CourseTemplateDto;
import com.geekschool.dto.LectionDto;
import com.geekschool.mapper.CourseTemplateMapper;
import com.geekschool.service.CourseTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class CourseTemplateRestController {


    private CourseTemplateService courseTemplateService;
    private CourseTemplateMapper courseTemplateMapper;

    @GetMapping(value = "/courses-templates")
    public List<LectionDto> getCreateCourseTemplateForm() {

        return courseTemplateService.getLectionDtoS();
    }

    @PostMapping(value = "/course-templates")
    public void sendInvitation(@RequestBody CourseTemplateDto courseTemplateDto) {
        courseTemplateService.saveCourseTemplate(courseTemplateDto);
    }

    @GetMapping("${api}/courses-template")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseTemplateDto> getCoursesTemplate() {
        return courseTemplateService.getAllCourses()
                .stream()
                .map(c -> courseTemplateMapper.convertToCourseTemplateDto(c))
                .collect(Collectors.toList());
    }
}
