package com.geekschool.service;

import com.geekschool.dto.SubjectDto;
import com.geekschool.entity.Course;
import com.geekschool.mapper.CourseMapper;
import com.geekschool.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;
    private CourseMapper courseMapper;

    @Transactional
    public Course findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Transactional
    public List<SubjectDto> getSubjects() {
        return courseRepository.findAll().stream()
                .map(subject -> courseMapper.convertToSubjectDto(subject))
                .collect(Collectors.toList());
    }
}
