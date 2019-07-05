package com.geekschool.entity.helper;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class CourseTemplateHelper {
    private Long id;
    private String name;
    private String direction;
    private List<String> lections;
}
