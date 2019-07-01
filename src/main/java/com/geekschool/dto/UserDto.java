package com.geekschool.dto;

import com.geekschool.constants.Role;
import com.geekschool.constants.Status;
import com.geekschool.entity.Group;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Status status;
    private Role role;
    private List<Group> groups;
}
