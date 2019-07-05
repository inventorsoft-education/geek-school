package com.geekschool.controllers;

import com.geekschool.entity.CourseTemplate;
import com.geekschool.entity.Lection;
import com.geekschool.entity.helper.CourseTemplateHelper;
import com.geekschool.repository.CourseTemplateRepository;
import com.geekschool.repository.LectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseTemplateRestController {

    private LectionRepository lectionRepository;
    private CourseTemplateRepository courseTemplateRepository;
    private List<Lection> lectionList;

    @Autowired
    public CourseTemplateRestController(LectionRepository lectionRepository1, CourseTemplateRepository courseTemplateRepository1, List<Lection> lectionList1){
        this.lectionRepository = lectionRepository1;
        this.courseTemplateRepository = courseTemplateRepository1;
        this.lectionList = lectionList1;
    }


    @PostMapping(value = "/saveCourseTemplate")
    public void sendInvitation(@RequestBody CourseTemplateHelper courseTemplateHelper){

        lectionList.clear();

        CourseTemplate courseTemplate = new CourseTemplate();
        courseTemplate.setName(courseTemplateHelper.getName());
        courseTemplate.setDirection(courseTemplateHelper.getDirection());

        for (String lectionName: courseTemplateHelper.getLections() ) {
            if(lectionRepository.findByName(lectionName).isPresent()){
                lectionList.add(lectionRepository.findByName(lectionName).get());
            }
        }

        courseTemplate.setLections(lectionList);

        courseTemplateRepository.save(courseTemplate);

    }
}
