package com.geekschool.controllers;

import com.geekschool.entity.InvitedToken;
import com.geekschool.entity.Role;
import com.geekschool.entity.User;
import com.geekschool.repository.InvitedTokenRepository;
import com.geekschool.service.ForgotPasswordService;
import com.geekschool.service.InvitationService;
import com.geekschool.service.mail.MailSenderService;
import com.geekschool.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class InvitationRestController {

    private InvitationService invitationService;
    private InvitedTokenRepository invitedTokenRepository;
    private ForgotPasswordService forgotPasswordService;

    @GetMapping(value = "/invitation")
    public void sendInvitation(@RequestParam Role formType, String email){
        invitationService.sendInvitation(formType,email);
    }

    @GetMapping(value = "/invitation/user/token")
    public Long getInformationByToken(@RequestParam String token){
        InvitedToken invitedToken = invitedTokenRepository.findByToken(token);
        if(forgotPasswordService.checkToken(invitedToken)){
            return invitedToken.getUser().getId();
        }
        return null;
    }



}
