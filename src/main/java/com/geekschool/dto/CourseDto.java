package com.geekschool.dto;

import com.geekschool.entity.Status;
import com.geekschool.entity.Lection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private long id;

    private String name;

    private String description;

    private Status status;

    private List<Lection> lections;
}
