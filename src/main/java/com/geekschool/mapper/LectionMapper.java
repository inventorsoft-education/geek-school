package com.geekschool.mapper;

import com.geekschool.dto.LectionDto;
import com.geekschool.entity.Lection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LectionMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private List<LectionDto> lectionDtos;

    public LectionDto convertToLectionDto(Lection lection) {
        return new LectionDto(
                lection.getId(),
                lection.getName(),
                lection.getDescription(),
                userMapper.convertToUserDto(lection.getTeacher())
        );
    }

    public List<LectionDto> convertToLectionDtoS(List<Lection> lections){

        lectionDtos.clear();

        for (Lection lection: lections) {
            lectionDtos.add(convertToLectionDto(lection));
        }

        return  lectionDtos;

    }
}
