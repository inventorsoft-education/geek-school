package com.geekschool.dto.lections;

import com.geekschool.dto.UserDto;
import com.geekschool.entity.CourseLection;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class LectionDto {
    private long id;
    private String name;
    private String description;
    private UserDto teacher;
    private List<CourseLection> courseLections;
}
