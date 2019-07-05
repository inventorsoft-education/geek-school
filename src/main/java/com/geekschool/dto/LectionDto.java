package com.geekschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectionDto {
    private long id;
    private String name;
    private String description;
    private UserDto teacher;
}
