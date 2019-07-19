package com.geekschool.dto.lections;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "name")
@AllArgsConstructor
public class LectionValidDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String teacherName;
}
