package com.geekschool.mapper;

import com.geekschool.dto.LectionDto;
import com.geekschool.entity.Lection;
import org.springframework.stereotype.Component;

@Component
public class LectionMapper {

    public LectionDto convertToLectionDto(Lection lection) {
        return new LectionDto(
                lection.getId(),
                lection.getName(),
                lection.getDescription(),
                lection.getTeacher(),
                lection.getCourse());
    }
}
