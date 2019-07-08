package com.geekschool.controllers;

import com.geekschool.entity.Role;
import com.geekschool.entity.User;
import com.geekschool.service.ForgotPasswordService;
import com.geekschool.service.InvitationService;
import com.geekschool.service.mail.MailSenderService;
import com.geekschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class InvitationRestController {

    private InvitationService invitationService;

    @GetMapping(value = "/invitation")
    public void sendInvitation(@RequestParam Role formType, String email){
        invitationService.sendInvitation(formType,email);
    }

}
