package com.geekschool.mapper;

import com.geekschool.dto.LectionDto;
import com.geekschool.entity.Lection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class LectionMapper {

    private UserMapper userMapper;
    private List<LectionDto> lectionDtos;

    public LectionDto convertToLectionDto(Lection lection) {
        return new LectionDto(
                lection.getId(),
                lection.getName(),
                lection.getDescription(),
                userMapper.convertToUserDto(lection.getTeacher()),
                lection.getCourseLections()
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
