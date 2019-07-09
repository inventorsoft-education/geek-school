package com.geekschool.controllers;

import com.geekschool.dto.CourseTemplateDto;
import com.geekschool.dto.LectionDto;
import com.geekschool.entity.CourseTemplate;
import com.geekschool.entity.Lection;
import com.geekschool.mapper.LectionMapper;
import com.geekschool.repository.CourseTemplateRepository;
import com.geekschool.repository.LectionRepository;
import com.geekschool.service.CourseTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@AllArgsConstructor
@RestController
public class CourseTemplateRestController {


    private CourseTemplateService courseTemplateService;

    @GetMapping(value = "/courses-templates")
    public List<LectionDto> getCreateCourseTemplateForm() {

        return courseTemplateService.getLectionDtoS();
    }

    @PostMapping(value = "/course-templates")
    public void sendInvitation(@RequestBody CourseTemplateDto courseTemplateDto) {

        courseTemplateService.saveCourseTemplate(courseTemplateDto);

    }
}
