package com.geekschool.controllers;

import com.geekschool.entity.InvitedToken;
import com.geekschool.entity.User;
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

    @GetMapping(value = "/user/{token}")
    public ModelAndView getUserForm(@PathVariable String token) {
        InvitedToken invitedToken = invitedTokenRepository.findByToken(token);
        ModelAndView mv = new ModelAndView();
        if(forgotPasswordService.checkToken(invitedToken)){
            User user = invitedToken.getUser();
            mv.addObject("user", user);
            mv.addObject("invitedToken", invitedToken);
            mv.setViewName("invitations/form");
            mv.getModel();
            return mv;
        }
        else {
            mv.setViewName("errors/tokenError");
            mv.getModel();
            return mv;
        }
    }

}
