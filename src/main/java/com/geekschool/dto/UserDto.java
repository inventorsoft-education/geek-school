package com.geekschool.dto;

import com.geekschool.constants.Status;
import com.geekschool.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

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

}
