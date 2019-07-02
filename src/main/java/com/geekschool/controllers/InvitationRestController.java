package com.geekschool.controllers;

import com.geekschool.entity.User;
import com.geekschool.service.ForgotPasswordService;
import com.geekschool.service.InvitationService;
import com.geekschool.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.UUID;

@RestController
@RequestMapping
public class InvitationRestController {

    private UserServiceImpl userService;
    private MailSenderService mailSenderService;
    private ForgotPasswordService forgotPasswordService;
    private InvitationService invitationService;

    @Autowired
    public InvitationRestController(UserServiceImpl userService, MailSenderService mailSenderService1, ForgotPasswordService forgotPasswordService1, InvitationService invitationService1){
        this.userService = userService;
        this.mailSenderService = mailSenderService1;
        this.forgotPasswordService = forgotPasswordService1;
        this.invitationService = invitationService1;
    }


    @Transactional
    @PostMapping(value = "/invitation")
    public void sendInvitation(@RequestParam String formType, String email){

        UUID uuid = UUID.randomUUID();
        String link = forgotPasswordService.createTokenLink(uuid);
        User user = userService.createUserByToken(formType, email);
        invitationService.createInvitedToken(uuid, user);
        mailSenderService.sendMessage(email, link);

    }

}
