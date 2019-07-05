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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.UUID;

// TODO: 2019-07-05 refactor to rest controller
@Controller
@AllArgsConstructor
public class ForgotPasswordRestController {

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
        User user = userRepository.findByUsername(login).get();
        invitationService.createInvitedToken(uuid, user);
        mailSenderService.sendMessage(user.getEmail(), link);

        return "blog-home";
    }

    // TODO: 2019-07-05 refactor to view controller
    @GetMapping(value = "/forgotPassword")
    public String  getForgotPasswordForm(){
        return "admin/forgotPasswordForm";
    }

    // TODO: 2019-07-05 add dedicated rest endpoint to check token existance/state
    @GetMapping(value = "/user/password/{token}")
    public ModelAndView getForgotPasswordForm(@PathVariable String token){

        InvitedToken invitedToken = invitedTokenRepository.findByToken(token);
        return forgotPasswordService.checkToken(invitedToken, "passwords/forgotPassword");

    }


}
