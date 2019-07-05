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

    private UserService userService;
    private MailSenderService mailSenderService;
    private ForgotPasswordService forgotPasswordService;
    private InvitationService invitationService;

    // TODO: 2019-07-05 move transactional into service layer. move logic into service layer
    @Transactional
    @PostMapping(value = "/invitation")
    public void sendInvitation(@RequestParam Role formType, String email){

        UUID uuid = UUID.randomUUID();
        String link = forgotPasswordService.createTokenLink(uuid);
        User user = userService.createUserByToken(formType, email);
        invitationService.createInvitedToken(uuid, user);
        mailSenderService.sendMessage(email, link);

    }

}
