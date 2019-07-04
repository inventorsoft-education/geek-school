package com.geekschool.controllers;

import com.geekschool.entity.CourseTemplate;
import com.geekschool.entity.Lection;
import com.geekschool.repository.LectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CourseTemplateController {

    private LectionRepository lectionRepository;

    @Autowired
    public CourseTemplateController(LectionRepository lectionRepository1){
        this.lectionRepository = lectionRepository1;
    }

    @GetMapping(value = "/createCourseTemplate")
    public ModelAndView getCreateCourceTemplateForm() {
        CourseTemplate courseTemplate = new CourseTemplate();
        ModelAndView mv = new ModelAndView();
        List<Lection> lections = lectionRepository.findAll();
        mv.setViewName("courses/createCourseTemplate");
        mv.addObject("courseTemplate", courseTemplate);
        mv.addObject("lections", lections);
        return mv;
    }

}
