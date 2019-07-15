package com.geekschool.controllers;

import com.geekschool.entity.InvitedToken;
import com.geekschool.entity.User;
import com.geekschool.repository.InvitedTokenRepository;
import com.geekschool.repository.UserRepository;
import com.geekschool.service.ForgotPasswordService;
import com.geekschool.service.InvitationService;
import com.geekschool.service.mail.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.PermitAll;
import javax.transaction.Transactional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ForgotPasswordRestController {

    private ForgotPasswordService forgotPasswordService;
    private InvitedTokenRepository invitedTokenRepository;

    @GetMapping(value = "/forgot-password-send")
    public void restorePassword(@RequestParam String login){
        forgotPasswordService.sendRestorePasswordLink(login);
    }

    @GetMapping(value = "/password/user/token")
    public Long getInformationByToken(@RequestParam String token){
        InvitedToken invitedToken = invitedTokenRepository.findByToken(token);
        if(forgotPasswordService.checkToken(invitedToken)){
            return invitedToken.getUser().getId();
        }
        return null;
    }
}
