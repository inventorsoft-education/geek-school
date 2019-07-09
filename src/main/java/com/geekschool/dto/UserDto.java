package com.geekschool.dto;

import com.geekschool.entity.Role;
import com.geekschool.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private long id;
    private String username;
    private String email;
    private Status status;
    private Role role;
    private List<GroupDto> groups;
}
