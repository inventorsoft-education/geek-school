package com.geekschool.mapper;

import com.geekschool.dto.LectionDto;
import com.geekschool.entity.Lection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LectionMapper {

    @Autowired
    private UserMapper userMapper;

    public LectionDto convertToLectionDto(Lection lection) {
        return new LectionDto(
                lection.getId(),
                lection.getName(),
                lection.getDescription(),
                userMapper.convertToUserDto(lection.getTeacher()),
                lection.getCourse());
    }
}
