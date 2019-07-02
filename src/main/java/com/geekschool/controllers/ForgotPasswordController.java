package com.geekschool.controllers;

import com.geekschool.entity.InvitedToken;
import com.geekschool.entity.User;
import com.geekschool.repository.InvitedTokenRepository;
import com.geekschool.repository.UserRepository;
import com.geekschool.service.ForgotPasswordService;
import com.geekschool.service.InvitationService;
import com.geekschool.service.MailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class ForgotPasswordController {

    private ForgotPasswordService forgotPasswordService;
    private UserRepository userRepository;
    private MailSenderService mailSenderService;
    private InvitationService invitationService;
    private InvitedTokenRepository invitedTokenRepository;

    @Transactional
    @PostMapping(value = "/forgotPassword")
    public String restorePassword(@RequestParam String login){

        UUID uuid = UUID.randomUUID();
        String link = forgotPasswordService.createForgotPasswordLink(uuid);
        User user = userRepository.findByUsername(login);
        invitationService.createInvitedToken(uuid, user);
        mailSenderService.sendMessage(user.getEmail(), link);

        return "blog-home";
    }

    @GetMapping(value = "/forgotPassword")
    public String  getForgotPasswordForm(){
        return "admin/forgotPasswordForm";
    }

    @GetMapping(value = "/user/password/{token}")
    public ModelAndView getForgotPasswordForm(@PathVariable String token){

        InvitedToken invitedToken = invitedTokenRepository.findByToken(token);
        return forgotPasswordService.checkToken(invitedToken, "passwords/forgotPassword");

    }


}
