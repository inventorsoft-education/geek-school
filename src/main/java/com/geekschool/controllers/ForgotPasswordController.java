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

@Controller
@AllArgsConstructor
public class ForgotPasswordController {

    private InvitedTokenRepository invitedTokenRepository;
    private ForgotPasswordService forgotPasswordService;

    @GetMapping(value = "/user/password/{token}")
    public ModelAndView getForgotPasswordForm(@PathVariable String token){

        InvitedToken invitedToken = invitedTokenRepository.findByToken(token);
        ModelAndView mv = new ModelAndView();
        if(forgotPasswordService.checkToken(invitedToken)){
            User user = invitedToken.getUser();
            mv.setViewName("passwords/forgotPassword");
            mv.addObject("invitedToken", invitedToken);
            mv.addObject("user", user);
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
