package com.geekschool.mapper;

import com.geekschool.dto.SubjectDto;
import com.geekschool.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public SubjectDto convertToSubjectDto(Course course) {
        return new SubjectDto(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getStatus(),
                course.getLections());
    }
}
