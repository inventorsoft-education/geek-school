package com.geekschool.dto;

import com.geekschool.entity.Subject;
import com.geekschool.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private long id;

    private String name;

    private String description;

    private List<Subject> subjects;

    private Set<User> students;
}
