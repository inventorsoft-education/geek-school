package com.geekschool.mapper;

import com.geekschool.dto.courses.CourseDto;
import com.geekschool.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDto convertToSubjectDto(Course course) {
        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getStatus(),
                course.getCourseLection());
    }
}

