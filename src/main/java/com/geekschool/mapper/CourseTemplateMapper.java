package com.geekschool.mapper;

import com.geekschool.dto.CourseTemplateDto;
import com.geekschool.entity.CourseTemplate;
import com.geekschool.repository.LectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CourseTemplateMapper {

    private LectionRepository lectionRepository;
    private LectionMapper lectionMapper;

    public CourseTemplate convertToCourseTemplate(CourseTemplateDto courseTemplateDto){

        CourseTemplate courseTemplate = new CourseTemplate();
        courseTemplate.setName(courseTemplateDto.getName());
        courseTemplate.setDirection(courseTemplateDto.getDirection());
        //courseTemplate.setLections(lectionRepository.findByNameIn(courseTemplateDto.getLections()));
        //courseTemplate.setLections(courseTemplateDto.getLections());

        return courseTemplate;
    }

    public CourseTemplateDto convertToCourseTemplateDto(CourseTemplate courseTemplate) {
        return new CourseTemplateDto(
                courseTemplate.getId(),
                courseTemplate.getName(),
                courseTemplate.getDirection(),
                courseTemplate.getLections()
                        .stream()
                        .map(lection -> lectionMapper.convertToLectionDto(lection))
                        .collect(Collectors.toList())
        );
    }
}

