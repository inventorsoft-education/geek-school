package com.geekschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LectionDto {
    private long id;
    private String name;
    private String description;
    private UserDto teacher;
}
