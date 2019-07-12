package com.geekschool.service;

import com.geekschool.dto.CourseTemplateDto;
import com.geekschool.dto.LectionDto;
import com.geekschool.entity.CourseTemplate;
import com.geekschool.mapper.CourseTemplateMapper;
import com.geekschool.mapper.LectionMapper;
import com.geekschool.repository.CourseTemplateRepository;
import com.geekschool.repository.LectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseTemplateService {

    private CourseTemplateMapper courseTemplateMapper;
    private CourseTemplateRepository courseTemplateRepository;
    private LectionRepository lectionRepository;
    private LectionMapper lectionMapper;

    public void saveCourseTemplate(CourseTemplateDto courseTemplateDto){

        CourseTemplate courseTemplate = courseTemplateMapper.convertToCourseTemplate(courseTemplateDto);
        courseTemplateRepository.save(courseTemplate);

    }

    public List<LectionDto> getLectionDtoS(){

        return lectionMapper.convertToLectionDtoS(lectionRepository.findAll());
    }

    @Transactional
    public List<CourseTemplate> getAllCourses() {
        return courseTemplateRepository.findAll();
    }

    @Transactional
    public CourseTemplate findById(Long id) {
        return courseTemplateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

