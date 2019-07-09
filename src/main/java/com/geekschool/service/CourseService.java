package com.geekschool.service;

import com.geekschool.entity.Course;
import com.geekschool.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;

    @Transactional
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Transactional
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }
}
