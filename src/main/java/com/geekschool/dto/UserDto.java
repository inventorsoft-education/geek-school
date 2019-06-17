package com.geekschool.dto;

import com.geekschool.constants.Status;
import com.geekschool.entity.Role;
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
    private List<Role> roles;

}
