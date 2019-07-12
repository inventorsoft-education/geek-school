package com.geekschool.dto;

import com.geekschool.entity.CourseLection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectionDto {
    private long id;
    private String name;
    private String description;
    private UserDto teacher;
    private List<CourseLection> courseLections;
}
