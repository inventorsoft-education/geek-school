package com.geekschool.controllers;

import com.geekschool.entity.InvitedToken;
import com.geekschool.repository.InvitedTokenRepository;
import com.geekschool.service.ForgotPasswordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


@AllArgsConstructor
@Controller
public class InvitationController {

    private InvitedTokenRepository invitedTokenRepository;
    private ForgotPasswordService forgotPasswordService;

    @GetMapping(value = "/invitation")
    public String index() {
        return "admin/invitation";
    }

    // TODO: 2019-07-05 move loading of token into service layer
    @GetMapping(value = "/user/{token}")
    public ModelAndView getUserForm(@PathVariable String token) {
        InvitedToken invitedToken = invitedTokenRepository.findByToken(token);
        return this.forgotPasswordService.checkToken(invitedToken, "invitations/form");
    }
}
