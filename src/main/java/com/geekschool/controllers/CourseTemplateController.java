package com.geekschool.controllers;

import com.geekschool.entity.CourseTemplate;
import com.geekschool.entity.Lection;
import com.geekschool.repository.LectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@AllArgsConstructor
@Controller
public class CourseTemplateController {

    private LectionRepository lectionRepository;

    // TODO: 2019-07-05 do not load data on template. Perhaps, refactor to view controller
    @GetMapping(value = "/createCourseTemplate")
    public ModelAndView getCreateCourseTemplateForm() {
        CourseTemplate courseTemplate = new CourseTemplate();
        ModelAndView mv = new ModelAndView();
        List<Lection> lections = lectionRepository.findAll();
        mv.setViewName("courses/createCourseTemplate");
        mv.addObject("courseTemplate", courseTemplate);
        mv.addObject("lections", lections);
        return mv;
    }

}
