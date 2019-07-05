package com.geekschool.service;

import com.geekschool.entity.InvitedToken;
import com.geekschool.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class ForgotPasswordService {

    @Value("${invitation.default.host}")
    private String host;

    // TODO: 2019-07-05 julia sama vse vypravyt
    public String createTokenLink(UUID uuid){
        return host+"user/"+uuid;
    }

    public String createForgotPasswordLink(UUID uuid){
        return host+"user/password/"+uuid;
    }

    // TODO: 2019-07-05 get rid of ModelAndView inside service layer
    public ModelAndView checkToken(InvitedToken invitedToken, String view){
        User user = invitedToken.getUser();
        ModelAndView mv = new ModelAndView();
        if(LocalDate.now().isBefore(invitedToken.getDateOff())){
            mv.setViewName(view);
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
