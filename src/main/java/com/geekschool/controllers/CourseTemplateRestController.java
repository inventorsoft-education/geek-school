package com.geekschool.controllers;

import com.geekschool.dto.CourseTemplateDto;
import com.geekschool.entity.CourseTemplate;
import com.geekschool.entity.Lection;
import com.geekschool.repository.CourseTemplateRepository;
import com.geekschool.repository.LectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class CourseTemplateRestController {

    private LectionRepository lectionRepository;
    private CourseTemplateRepository courseTemplateRepository;
    private List<Lection> lectionList;

    // TODO: 2019-07-05 change url to /course-templates
    // TODO: 2019-07-05 refactor logic into service layer
    @PostMapping(value = "/saveCourseTemplate")
    public void sendInvitation(@RequestBody CourseTemplateDto courseTemplateDto) {

        lectionList.clear();

        CourseTemplate courseTemplate = new CourseTemplate();
        courseTemplate.setName(courseTemplateDto.getName());
        courseTemplate.setDirection(courseTemplateDto.getDirection());

        for (String lectionName : courseTemplateDto.getLections()) {
            if (lectionRepository.findByName(lectionName).isPresent()) {
                lectionList.add(lectionRepository.findByName(lectionName).get());
            }
        }

        courseTemplate.setLections(lectionList);
        courseTemplateRepository.save(courseTemplate);

    }
}
