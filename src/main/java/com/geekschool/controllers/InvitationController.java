package com.geekschool.controllers;

import com.geekschool.entity.InvitedToken;
import com.geekschool.repository.InvitedTokenRepository;
import com.geekschool.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class InvitationController {

    private InvitedTokenRepository invitedTokenRepository;
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    public InvitationController(InvitedTokenRepository invitedTokenRepository1, ForgotPasswordService forgotPasswordService1){
        this.invitedTokenRepository = invitedTokenRepository1;
        this.forgotPasswordService = forgotPasswordService1;
    }

    @GetMapping(value = "/invitation")
    public String index() {
        return  "admin/invitation";
    }

    @GetMapping(value = "/user/{token}")
    public ModelAndView getUserForm(@PathVariable String token){
        InvitedToken invitedToken = invitedTokenRepository.findByToken(token);
        return this.forgotPasswordService.checkToken(invitedToken, "invitations/form");
    }
}
