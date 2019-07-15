package com.geekschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class CourseTemplateDto {
    private Long id;
    private String name;
    private String direction;
    private List<LectionDto> lections;
}
