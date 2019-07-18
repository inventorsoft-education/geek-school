package com.geekschool.service;

import com.geekschool.dto.courses.CourseLectionsDto;
import com.geekschool.dto.lections.LectionDateDto;
import com.geekschool.entity.*;
import com.geekschool.repository.CourseRepository;
import com.geekschool.repository.CourseTemplateRepository;
import com.geekschool.repository.LectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;
    private CourseTemplateRepository courseTemplateRepository;

    private LectionRepository lectionRepository;

    @Transactional
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Transactional
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Transactional
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Course createCourseOnTheTemplate(CourseLectionsDto lectionDtoList) {
        Course course = new Course();
        CourseTemplate courseTemplate = courseTemplateRepository.findById(lectionDtoList.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        course.setName(courseTemplate.getName());
        course.setDescription(courseTemplate.getDirection());
        course.setStatus(Status.ACTIVE);

        List<LectionDateDto> lections = lectionDtoList.getLectionDateDtoList();

        List<CourseLection> courseLections = new ArrayList<>();

        for (int i = 0; i <= lections.size() - 1; i++) {
            courseLections.add(new CourseLection(
                    course,
                    lectionRepository.findById(lections.get(i).getIdLection()).get(),
                    lections.get(i).getStartDate(),
                    lections.get(i).getEndDate()));
        }

        course.setCourseLection(courseLections);

        courseRepository.save(course);

        return course;
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
