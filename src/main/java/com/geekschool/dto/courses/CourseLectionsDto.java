package com.geekschool.dto.courses;

import com.geekschool.dto.lections.LectionDateDto;
import com.geekschool.validation.LectionDatesNotOverlap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseLectionsDto {

    private Long id;

    @Valid
    @LectionDatesNotOverlap
    private List<LectionDateDto> lectionDateDtoList;

}
