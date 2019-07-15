package com.geekschool.dto;

import com.geekschool.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private long id;

    private String name;

    private String description;

    private Course subject;
}
