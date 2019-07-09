package com.geekschool.mapper;

import com.geekschool.dto.CourseTemplateDto;
import com.geekschool.entity.CourseTemplate;
import com.geekschool.repository.LectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseTemplateMapper {

    @Autowired
    private LectionRepository lectionRepository;

    public CourseTemplate convertToCourseTemplate(CourseTemplateDto courseTemplateDto){

        CourseTemplate courseTemplate = new CourseTemplate();
        courseTemplate.setName(courseTemplateDto.getName());
        courseTemplate.setDirection(courseTemplateDto.getDirection());
        courseTemplate.setLections(lectionRepository.findByNameIn(courseTemplateDto.getLections()));

        return courseTemplate;
    }
}
