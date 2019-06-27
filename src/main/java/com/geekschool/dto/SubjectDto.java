package com.geekschool.dto;

import com.geekschool.constants.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    private long id;

    private String name;

    private String description;

    private Status status;
}
