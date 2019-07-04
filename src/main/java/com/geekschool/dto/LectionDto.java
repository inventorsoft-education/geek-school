package com.geekschool.dto;

import com.geekschool.entity.Subject;
import com.geekschool.entity.User;
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
    private User teacher;
}
