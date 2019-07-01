package com.geekschool.mapper;

import com.geekschool.dto.SubjectDto;
import com.geekschool.entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public SubjectDto convertToSubjectDto(Subject subject) {
        return new SubjectDto(
                subject.getId(),
                subject.getName(),
                subject.getDescription(),
                subject.getStatus(),
                subject.getLections());
    }
}
