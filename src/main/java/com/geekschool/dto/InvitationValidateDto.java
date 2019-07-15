package com.geekschool.dto;

import com.geekschool.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
public class InvitationValidateDto {

    private Role formType;

    @Email(message = "{validation.invitation.email.email}")
    @NotEmpty(message = "{validation.invitation.email.empty}")
    private String email;
}