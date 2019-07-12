package com.geekschool.service;

import com.geekschool.dto.LectionDto;
import com.geekschool.entity.*;
import com.geekschool.mapper.LectionMapper;
import com.geekschool.repository.CourseRepository;
import com.geekschool.repository.CourseTemplateRepository;
import com.geekschool.repository.LectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;
    private CourseTemplateRepository courseTemplateRepository;

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
    public Course createCourseOnTheTemplate(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        Course course = new Course();
        CourseTemplate courseTemplate = courseTemplateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        course.setName(courseTemplate.getName());
        course.setDescription(courseTemplate.getDirection());
        course.setStatus(Status.ACTIVE);

        List<Lection> lections = courseTemplate.getLections();

        List<CourseLection> courseLections = new ArrayList<>();

        for (int i = 0; i <= lections.size() - 1; i++) {
            courseLections.add(new CourseLection(
                    course,
                    lections.get(i),
                    startDate,
                    endDate));
        }

        course.setCourseLection(courseLections);

        courseRepository.save(course);

        return course;
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
